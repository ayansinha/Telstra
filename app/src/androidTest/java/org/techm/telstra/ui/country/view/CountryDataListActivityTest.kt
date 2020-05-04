package org.techm.telstra.ui.country.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import org.techm.telstra.R

class CountryDataListActivityTest {

    @get : Rule
    val countryActivityRule: ActivityScenarioRule<CountryDataListActivity> = ActivityScenarioRule(CountryDataListActivity::class.java)

    @Test
    fun recyclerViewTestScrolling() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun recyclerViewTestScrollingToPositionEndIndex() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).perform(ViewActions.swipeUp())
    }

    @Test
    fun recyclerViewTestScrollingToPositionTop() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).perform(ViewActions.swipeDown())
    }
}