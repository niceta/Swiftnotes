package com.moonpi.swiftnotes

import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.uiautomator.UiDevice

import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.withText

open class Page(protected var device: UiDevice?) {

    var menu = Espresso.onView(withContentDescription("More options"))

    open fun isDisplayed(element: ViewInteraction) {
        element.check(matches(ViewMatchers.isDisplayed()))
    }

    open fun isDisplayed(s: String) {
        Espresso.onView(withText(s)).check(matches(ViewMatchers.isDisplayed()))
    }

    fun menuClick() {
        menu.perform(click())
    }

    fun closeMenu() {
        Espresso.pressBack()
    }
}
