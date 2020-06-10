package com.example.traveljournal

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.junit.MockitoJUnit.rule

//import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.traveljournal.views.MainActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class ChangeTextBehaviorTest {

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

//
//    @Test
//    fun changeText_CreateJournalActivity() {
//        // Type text and then press the button.
//        onView(withId(R.id.editJournalName))
//            .perform(typeText(stringToBetyped), closeSoftKeyboard())
//        onView(withId(R.id.JournalNext)).perform(click())
//
//        // Check that the text was changed.
//        onView(withId(R.id.textToBeChanged))
//            .check(matches(withText(stringToBetyped)))
//    }
}