package com.skills.newsapp.utils

import androidx.test.espresso.IdlingResource

/**
 *  This class is responsible for handling Expresso Testing in case of Asycronous calls
 *  @Increment will tell expresso to wait for testing and decrement will resume testing onwards
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
object EspressoIdlingResource {
    var countingIdlingResource: SimpleCountingIdlingResource? = SimpleCountingIdlingResource()
    fun increment() {
        if (countingIdlingResource != null) countingIdlingResource!!.increment()
    }

    fun decrement() {
        if (countingIdlingResource != null) countingIdlingResource!!.decrement()
    }

    val idlingResource: IdlingResource?
        get() = countingIdlingResource
}