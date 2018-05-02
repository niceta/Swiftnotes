package com.moonpi.swiftnotes

import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiObject2

import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.withHint
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

class EditPage(device: UiDevice?) : Page(device) {
    var noteTitle = Espresso.onView(ViewMatchers.withId(R.id.titleEdit))
    var noteBody = Espresso.onView(withId(R.id.bodyEdit))
    //public ViewInteraction backButton = Espresso.onView(withClassName(endsWith("ImageButton")));
    var backButton = device
            ?.findObject(By.clazz("android.widget.ImageButton").pkg("com.moonpi.swiftnotes"))
    var saveDialog = Espresso.onView(withText("Save changes?"))
    var yesButton = Espresso.onView(withId(android.R.id.button1))
    var noButton = Espresso.onView(withId(android.R.id.button2))

    fun isDisplayed(element: ViewInteraction, s: String) {
        element.check(matches(withHint(s)))
    }

    fun type(element: ViewInteraction, s: String) {
        element.perform(replaceText(s))
    }

    fun verifyText(element: ViewInteraction, s: String) {
        element.check(matches(withText(s)))
    }

    fun back() {
        backButton?.click()
    }

    fun clickYes() {
        yesButton.perform(click())
    }

    fun clickNo() {
        noButton.perform(click())
    }
}
