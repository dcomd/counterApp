package com.example.counters

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class CoroutineRule : TestRule {

    val testDispatcher = TestCoroutineDispatcher()

    override fun apply(base: Statement, description: Description) =
        object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                Dispatchers.setMain(testDispatcher)

                base.evaluate()

                cleanUp()
            }
        }

    private fun cleanUp() {
        // reset main after the test is done
        Dispatchers.resetMain()
        // call this to ensure TestCoroutineDispatcher doesn't
        // accidentally carry state to the next test
        testDispatcher.cleanupTestCoroutines()
    }
}