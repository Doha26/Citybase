package com.pavel.citybase.ui.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.hamcrest.CoreMatchers
import org.junit.Assert

class RecyclerViewItemCountAssertion(expectedCoun: Int) : ViewAssertion {
    private var expectedCount: Int = 0
    override fun check(
        view: View,
        noViewFoundException: NoMatchingViewException
    ) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        Assert.assertThat(
            adapter!!.itemCount,
            CoreMatchers.`is`(expectedCount)
        )
    }

    init {
        expectedCount = expectedCount
    }
}