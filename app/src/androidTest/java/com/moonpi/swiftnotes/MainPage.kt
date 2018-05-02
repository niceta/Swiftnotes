package com.moonpi.swiftnotes

import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.uiautomator.UiDevice

import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

class MainPage(device: UiDevice?) : Page(device) {
    var title = Espresso.onView(withText("Swiftnodes"))
    var newNoteButton = Espresso.onView(ViewMatchers.withId(R.id.newNote))
    var noNotes = Espresso.onView(withId(R.id.noNotes))

    var backupNotes = Espresso.onView(withText("Backup notes"))
    var restoreNotes = Espresso.onView(withText("Restore notes"))
    var rateApp = Espresso.onView(withText("Rate app"))

    override fun isDisplayed(element: ViewInteraction) {
        element.check(matches(ViewMatchers.isDisplayed()))
    }

    override fun isDisplayed(s: String) {
        Espresso.onView(withText(s)).check(matches(ViewMatchers.isDisplayed()))
    }

    fun addNote() {
        newNoteButton.perform(click())
    }
}
