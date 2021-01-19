import android.content.Context
import android.util.Log
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nine.R
import com.example.nine.ui.news.DogsFragment
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 *
 * UI Test
 *
 * Instrumented test, which will execute on an Android device.
 *
 * @author Kaan Osmanağaoğlu
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class UiTests : DogsFragment() {
    @Test
    fun launchFragment() {
        val frag = launchFragmentInContainer<DogsFragment>()
        frag.moveToState(Lifecycle.State.STARTED)

        onView(withId(R.id.dogsList))
            .perform(ViewActions.click())
            .perform(ViewActions.swipeLeft())
            .perform(ViewActions.swipeDown())
            .check { view, _ ->
                assertEquals(view.id, R.id.dogsList)
                Log.d("XXX", "view.id= ${view.id}")
                Log.d("XXX", "contentDescription: ${view.contentDescription}")
            }
    }

    @Test
    fun checkPortraitImages() {
        val frag = launchFragmentInContainer<DogsFragment>()
        frag.moveToState(Lifecycle.State.STARTED)

        onView(withId(R.id.newsImagePortrait))
            .perform(ViewActions.click())
            .check(matches(isDisplayed()))
    }

    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        val appContext: Context = ApplicationProvider.getApplicationContext()
        assertEquals("com.example.nine", appContext.packageName)
    }

//    @Test
//    fun checkLandscapeImages() {
//        val frag = launchFragmentInContainer<RecipesFragment>()
//        frag.moveToState(Lifecycle.State.STARTED)
//
//        onView(allOf(withId(R.id.recipeImageLandscape), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
//            .perform(ViewActions.click())
//            .check(matches(not(isCompletelyDisplayed()))) // TODO: Bug, matches(isDisplayed) or anything similar for multiple images not working for comparison.
//    }
}