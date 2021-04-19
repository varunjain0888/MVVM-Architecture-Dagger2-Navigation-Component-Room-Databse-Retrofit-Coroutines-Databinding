package com.skills.newsapp.utils

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicInteger

/**
 *  This class is responsible for handling Expresso Testing in case of Asycronous calls
 *  @Increment will tell expresso to wait for testing and decrement will resume testing onwards
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
class SimpleCountingIdlingResource : IdlingResource {
    private val counter = AtomicInteger(0)

    // written from main thread, read from any thread.
    @Volatile
    private var resourceCallback: IdlingResource.ResourceCallback? = null
    override fun getName(): String {
        return ""
    }

    override fun isIdleNow(): Boolean {
        return counter.get() == 0
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        resourceCallback = callback
    }

    /**
     * Increments the count of in-flight transactions to the resource being monitored.
     */
    fun increment() {
        counter.getAndIncrement()
    }

    /**
     * Decrements the count of in-flight transactions to the resource being monitored.
     * If this operation results in the counter falling below 0 - an exception is raised.
     *
     * @throws IllegalStateException if the counter is below 0.
     */
    fun decrement() {
        val counterVal = counter.decrementAndGet()
        if (counterVal == 0) {
            // we've gone from non-zero to zero. That means we're idle now! Tell espresso.
            if (resourceCallback != null) resourceCallback?.onTransitionToIdle()
        } else require(counterVal >= 0) { "Counter has been corrupted!" }
    }
}