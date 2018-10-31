package com.mhandharbeni.submissiondua

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.mhandharbeni.submissiondua.R.id.btnFavourite
import com.mhandharbeni.submissiondua.R.id.rvScore
import org.junit.Rule
import org.junit.Test

class InstrumentationTest{
    @Rule
    @JvmField var  activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour() {
        /*
        1. Menampilkan list RecyclerView
        2. klik list pada teks Barcelona
        3. Menampilkan button Favorit
        4. Klik button Favorit
        5. Back
        6. Klik tab Favorite
        7. klik list pada teks Barcelona
        8. Klik button Unfavorit
        9. Back
         */

        Thread.sleep(5000)
        onView(withId(R.id.rvScorePrevious)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(R.id.rvScorePrevious)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(hasDescendant(withText("Tottenham")), click()))
        Thread.sleep(3000)
        onView(withId(btnFavourite)).check(matches(isDisplayed()))
        onView(withId(btnFavourite)).perform(click())
        pressBack()
        onView(withText("Favourite Fixtures")).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.rvScoreFavourite)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(hasDescendant(withText("Tottenham")), click()))
        Thread.sleep(3000)
        onView(withId(btnFavourite)).perform(click())
        pressBack()
    }
}