package org.techm.telstra.ui.country.view

import android.content.Intent
import androidx.appcompat.app.ActionBar
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.techm.telstra.R

class CountryDataListActivityTest {

    @get: Rule
    val countryActivityRule: ActivityTestRule<CountryDataListActivity> = ActivityTestRule(CountryDataListActivity::class.java , false , false)

    @Before
    fun setUp() {
        val intent = Intent()
        countryActivityRule.launchActivity(intent)
    }

    @Test
    fun onLaunchActionBarTitleIsDisplayed() {
        val actionBar: ActionBar? = countryActivityRule.activity.supportActionBar
        Assert.assertNotNull(actionBar?.title)
    }

    @Test
    fun onLaunchCheckProgressBarIsDisplayed() {
        IdlingResource.ResourceCallback {
            onView(withId(R.id.progressBar))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun appLaunchSuccessfully() {
        ActivityScenario.launch(CountryDataListActivity::class.java)
    }

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