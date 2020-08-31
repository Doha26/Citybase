package com.pavel.citybase.ui

import android.content.Intent
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.pavel.citybase.R
import com.pavel.citybase.ui.main.MainActivity
import com.pavel.citybase.ui.util.CustomMatchers.Companion.withItemCount
import com.pavel.citybase.ui.util.RecyclerViewItemCountAssertion
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var mainActivityTestRule = ActivityTestRule(
        MainActivity::class.java
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
    fun test_search_valid_small_results() {
        onView(withId(R.id.searchViewCity))
            .perform(typeSearchViewText("Alberta"))
        closeSoftKeyboard()

        //ASSERT
       onView(withId(R.id.rvListCities))
            .check(matches(withItemCount(1)))
        onView(withId(R.id.tvNoResult))
            .check(
                matches(
                    CoreMatchers.not(isDisplayed())
                )
            )
    }

    @Test
    fun test_search_valid_big_results() {
        onView(withId(R.id.searchViewCity))
            .perform(typeSearchViewText("Alber"))
        closeSoftKeyboard()

        //ASSERT
        onView(withId(R.id.rvListCities))
            .check(matches(withItemCount(70)))
        onView(withId(R.id.tvNoResult))
            .check(
                matches(
                    CoreMatchers.not(isDisplayed())
                )
            )
    }

    @Test
    fun test_search_invalid() {
        onView(withId(R.id.searchViewCity))
            .perform(typeSearchViewText("zagal"))
        closeSoftKeyboard()

        //ASSERT
        onView(withId(R.id.rvListCities))
            .check(matches(withItemCount(0)))
        onView(withId(R.id.tvNoResult))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_city_name_on_map_toolbar() {
        onView(withId(R.id.searchViewCity))
            .perform(typeSearchViewText("Alberta"))
        closeSoftKeyboard()
        onView(withId(R.id.rvListCities))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        //ASSERT
        onView(withText("Meda, IT"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_search_is_case_sensitive() {
        onView(withId(R.id.searchViewCity))
            .perform(typeSearchViewText("s"))
        closeSoftKeyboard()

        //ASSERT
        onView(withId(R.id.rvListCities))
            .check(matches(withItemCount(11)))
    }

    @Test
    fun test_coming_back_from_Map() {
        onView(withId(R.id.searchViewCity))
            .perform(typeSearchViewText("Alberta"))
        closeSoftKeyboard()
        onView(withId(R.id.rvListCities))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        Espresso.pressBack()

        //ASSERT
        onView(withId(R.id.rvListCities))
            .check(matches(withItemCount(1)))
    }

    @Test
    fun test_on_back_button_click_go_to_cityList() {
        onView(withId(R.id.searchViewCity))
            .perform(typeSearchViewText("Alberta"))
       closeSoftKeyboard()
        onView(withId(R.id.rvListCities))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        onView(withId(R.id.ivBack)).perform(click());

        //ASSERT
        onView(withId(R.id.rvListCities))
            .check(matches(withItemCount(1)))
    }

    // Custom View Action for SearchView widget
    fun typeSearchViewText(text: String): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "Change view text"
            }
            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun perform(uiController: UiController?, view: View?) {
                (view as SearchView).setQuery(text, false)
            }
        }
    }
}