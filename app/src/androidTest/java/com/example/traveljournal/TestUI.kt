package com.example.traveljournal

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


import androidx.test.runner.AndroidJUnit4
import com.example.traveljournal.views.MainActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestUI {

    private lateinit var stringToBetyped: String

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso"
    }

    @Test
    fun onLaunchCreateJournalBtnIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.btnCreateJournal))
            .check(matches(isDisplayed()))
    }

    fun openCreateJournalActivity() {
        onView(withId(R.id.btnCreateJournal))
            .perform(click())
    }

    @Test
    fun NextBtnIsDisplayed_CreateJournalActivity() {
        ActivityScenario.launch(MainActivity::class.java)
        openCreateJournalActivity()

        onView(withId(R.id.JournalNext))
            .check(matches(isDisplayed()))
    }

    @Test
    fun changeText_CreateJournalActivity() {
        ActivityScenario.launch(MainActivity::class.java)

        openCreateJournalActivity()

        onView(withId(R.id.editJournalName))
            .perform(typeText(stringToBetyped))

        onView(withId(R.id.editJournalName))
            .check(matches(withText(stringToBetyped)))
    }


    @Test
    fun openCreateJournalPageActivity() {
        ActivityScenario.launch(MainActivity::class.java)

        openCreateJournalActivity()

        onView(withId(R.id.JournalNext))
            .perform(click())
    }
}