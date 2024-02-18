package barrera.alejandro.dondeloveo.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import barrera.alejandro.dondeloveo.R
import org.hamcrest.CoreMatchers.endsWith
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @Rule
    @JvmField
    val activityScenearioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNavigationToMediaContentDetailsFragment() {
        onView(withId(R.id.searchView))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withClassName(endsWith("SearchAutoComplete")))
            .perform(typeText("Garfield"), pressImeActionButton())

        Thread.sleep(5000)

        onView(withId(R.id.moreInfoButton))
            .check(matches(isDisplayed()))
            .perform(click())

        Thread.sleep(5000)

        onView(withId(R.id.descriptionTextView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testNavigationToFavoriteMediaContentDetailsFragment() {
        onView(withId(R.id.searchView))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withClassName(endsWith("SearchAutoComplete")))
            .perform(typeText("Garfield"), pressImeActionButton())

        Thread.sleep(5000)

        onView(withId(R.id.moreInfoButton))
            .check(matches(isDisplayed()))
            .perform(click())

        Thread.sleep(5000)

        onView(withId(R.id.button))
            .check(matches(isDisplayed()))
            .perform(click())

        Thread.sleep(5000)

        pressBack()

        Thread.sleep(5000)

        onView(withId(R.id.favoriteItem))
            .check(matches(isDisplayed()))
            .perform(click())

        Thread.sleep(5000)

        onView(withId(R.id.moreInfoButton))
            .check(matches(isDisplayed()))
            .perform(click())

        Thread.sleep(5000)

        onView(withId(R.id.descriptionTextView))
            .check(matches(isDisplayed()))
    }
}