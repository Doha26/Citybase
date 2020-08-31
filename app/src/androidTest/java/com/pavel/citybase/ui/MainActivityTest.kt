package com.pavel.citybase.ui

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.pavel.citybase.R
import com.pavel.citybase.ui.main.MainActivity
import com.pavel.citybase.ui.util.RecyclerViewItemCountAssertion
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    var mainActivityTestRule = ActivityTestRule(
        MainActivity::class.java, true, false
    )

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mainActivityTestRule.launchActivity(Intent())
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

    @Test
    fun testInitialState() {
        Espresso.closeSoftKeyboard()

        //ASSERT
        Espresso.onView(ViewMatchers.withId(R.id.searchViewCity))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }

    @Test
    fun testSearch_valid_small_results() {
        Espresso.onView(ViewMatchers.withId(R.id.tvNoResult))
            .perform(ViewActions.typeText("Alberta"))
        Espresso.closeSoftKeyboard()

        //ASSERT
        Espresso.onView(ViewMatchers.withId(R.id.rvListCities))
            .check(RecyclerViewItemCountAssertion(3))
        Espresso.onView(ViewMatchers.withId(R.id.tvNoResult))
            .check(
                ViewAssertions.matches(
                    CoreMatchers.not(ViewMatchers.isDisplayed())
                )
            )
    }

    @Test
    fun testSearch_valid_big_results() {
        Espresso.onView(ViewMatchers.withId(R.id.searchViewCity))
            .perform(ViewActions.typeText("Row"))
        Espresso.closeSoftKeyboard()

        //ASSERT
        Espresso.onView(ViewMatchers.withId(R.id.rvListCities))
            .check(RecyclerViewItemCountAssertion(25))
        Espresso.onView(ViewMatchers.withId(R.id.tvNoResult))
            .check(
                ViewAssertions.matches(
                    CoreMatchers.not(ViewMatchers.isDisplayed())
                )
            )
    }

    @Test
    fun testSearch_invalid() {
        Espresso.onView(ViewMatchers.withId(R.id.searchViewCity))
            .perform(ViewActions.typeText("zagal"))
        Espresso.closeSoftKeyboard()

        //ASSERT
        Espresso.onView(ViewMatchers.withId(R.id.rvListCities))
            .check(RecyclerViewItemCountAssertion(0))
        Espresso.onView(ViewMatchers.withId(R.id.tvNoResult))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testCityName_on_map_toolbar() {
        Espresso.onView(ViewMatchers.withId(R.id.searchViewCity))
            .perform(ViewActions.typeText("Sydney"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.rvListCities))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )

        //ASSERT
        Espresso.onView(ViewMatchers.withText("Alberta, CA"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withContentDescription(R.string.abc_action_bar_up_description))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testComing_back_from_Map() {
        Espresso.onView(ViewMatchers.withId(R.id.searchViewCity))
            .perform(ViewActions.typeText("Alberta"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.rvListCities))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        Espresso.pressBack()

        //ASSERT
        Espresso.onView(ViewMatchers.withId(R.id.searchViewCity))
            .check(ViewAssertions.matches(ViewMatchers.withText("Alberta")))
        Espresso.onView(ViewMatchers.withId(R.id.rvListCities))
            .check(RecyclerViewItemCountAssertion(3))
    }
}