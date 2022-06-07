package com.example.restaurant

import androidx.fragment.app.FragmentTransaction
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.restaurant.ui.cats.CatsBreedFragment
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CatsBreedsList {
    @Rule
    @JvmField
    val activity=ActivityTestRule(MainActivity::class.java)


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.newsapplication", appContext.packageName)
    }
   @Before
   fun testFragment():CatsBreedFragment {
        // Context of the app under test.
        activity.activity
        val transaction: FragmentTransaction =
            activity.activity.supportFragmentManager.beginTransaction()
        val catsFragment = CatsBreedFragment()
        transaction.add(catsFragment, "catsBrees")
        transaction.commit()
       return  catsFragment
    }
    @Test
    fun testCatBreed() {
        // Context of the app under test.

        onView(withId(R.id.recycle_cats_bred))
            .check(RecyclerViewItemCountAssertion(67))

     //   Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(id))).check(RecyclerViewItemCountAssertion(expectedItemCount))
    }
}