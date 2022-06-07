package com.example.restaurant

import android.view.View
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.NoMatchingViewException
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers

class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {
    override fun check(view: View, noViewFoundException: NoMatchingViewException) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        val recyclerViewItemCount = adapter!!.itemCount
        ViewMatchers.assertThat(
            "RecyclerView item count", recyclerViewItemCount, CoreMatchers.equalTo(
                expectedCount
            )
        )
    }
}