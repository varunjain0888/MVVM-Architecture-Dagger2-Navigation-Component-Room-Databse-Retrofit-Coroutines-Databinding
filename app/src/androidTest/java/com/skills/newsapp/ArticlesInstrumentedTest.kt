package com.skills.newsapp

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.skills.newsapp.ui.main.MainActivity
import com.skills.newsapp.ui.main.articlelist.ArticleListAdapter
import com.skills.newsapp.ui.main.articlelist.ArticlesListFragment
import com.skills.newsapp.utils.EspressoIdlingResource
import org.junit.*
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ArticlesInstrumentedTest {
    @get:Rule
    var mIntentsRule: ActivityTestRule<MainActivity?>? = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }
    @After
    fun unregisterUdlingResource(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.skills.newsapp", appContext.packageName)
    }

    @Test
    fun testRecyclerView() {
        onView(withId(R.id.rvList)).check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerScrollToPosition() {
        onView(withId(R.id.rvList)).perform(RecyclerViewActions.scrollToPosition<ArticleListAdapter.ArticleViewHolder>(15))
    }

    @Test
    fun testRecyclerViewItem() {
        onView(withRecyclerView(R.id.rvList).atPositionOnView(3, R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withRecyclerView(R.id.rvList).atPositionOnView(3, R.id.tvAuthor)).check(matches(isDisplayed()))
        onView(withRecyclerView(R.id.rvList).atPositionOnView(3, R.id.tvDate)).check(matches(isDisplayed()))
    }

    @Test
    fun testClick() {
        onView(withId(R.id.rvList)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2,click()))
    }

    @Test
    fun testDetailsFragment() {
        onView(withId(R.id.rvList)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2,click()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvContent)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAuthor)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }
}