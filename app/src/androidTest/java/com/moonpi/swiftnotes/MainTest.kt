package com.moonpi.swiftnotes

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.Until

import org.junit.Before
import org.junit.Rule
import org.junit.Test

import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withHint
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withTagKey
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.core.IsNull.notNullValue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat


class MainTest {

    private var mDevice: UiDevice? = null

    @Rule @JvmField
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun init() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        mDevice!!.pressHome()
        val launcherPackage = mDevice!!.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        mDevice!!.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT.toLong())
        val context = InstrumentationRegistry.getContext()
        val intent = context.packageManager.getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        mDevice!!.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT.toLong())
    }

    @Test
    fun test1() {
        val mainPage = MainPage(mDevice)
        mainPage.isDisplayed("Swiftnotes")
        mainPage.isDisplayed("Press '+' to add new note")
        mainPage.isDisplayed(mainPage.newNoteButton)
        mainPage.addNote()

        val editPage = EditPage(mDevice)
        editPage.isDisplayed(editPage.noteTitle, "Title")
        editPage.isDisplayed(editPage.noteBody, "Note")

        editPage.back()
        editPage.isDisplayed(editPage.saveDialog)
        editPage.isDisplayed(editPage.yesButton)
        editPage.isDisplayed(editPage.noButton)
        editPage.clickNo()

        mainPage.isDisplayed("Swiftnotes")
    }

    @Test
    fun test2() {
        val mainPage = MainPage(mDevice)
        mainPage.addNote()

        val editPage = EditPage(mDevice)
        editPage.isDisplayed(editPage.noteTitle, "Title")
        editPage.isDisplayed(editPage.noteBody, "Note")
        editPage.type(editPage.noteTitle, "Заметка 1")
        editPage.type(editPage.noteBody, "Тестовая запись 1")

        editPage.verifyText(editPage.noteTitle, "Заметка 1")
        editPage.verifyText(editPage.noteBody, "Тестовая запись 1")

        editPage.back()
        editPage.isDisplayed(editPage.saveDialog)
        editPage.clickYes()

        mainPage.isDisplayed("Swiftnotes")
        mainPage.isDisplayed("Заметка 1")
    }

    @Test
    fun test3() {
        val mainPage = MainPage(mDevice)
        mainPage.menuClick()
        mainPage.isDisplayed("Backup notes")
        mainPage.isDisplayed("Restore notes")
        mainPage.isDisplayed("Rate app")
        mainPage.closeMenu()
        mainPage.addNote()

        val editPage = EditPage(mDevice)
        editPage.menuClick()
        editPage.isDisplayed("Note font size")
        editPage.isDisplayed("Hide note body in list")
    }

    companion object {
        private val LAUNCH_TIMEOUT = 5000
        private val WAIT_TIMEOUT = 5000
        private val BASIC_SAMPLE_PACKAGE = "com.moonpi.swiftnotes"
    }
}