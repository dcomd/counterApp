package com.example.counters

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alexandre.counters.data.dbnetwork.dto.CountersDTO
import com.alexandre.counters.domain.model.Counters
import com.alexandre.counters.domain.useCase.CreateCounter
import com.alexandre.counters.domain.useCase.DeleteCounters
import com.alexandre.counters.domain.useCase.GetCounters
import com.alexandre.counters.domain.useCase.Update
import com.alexandre.counters.presentation.viewModel.CounterViewModel
import com.alexandre.counters.presentation.viewModel.CounterViewModelImp
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CounterTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = CoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CounterViewModel
    private lateinit var getCounters: GetCounters
    private lateinit var update: Update
    private lateinit var deleteCounters: DeleteCounters
    private lateinit var createCounter: CreateCounter
    private lateinit var context: Context


    @Before
    fun setup() {
        getCounters = mockk()
        update= mockk()
        deleteCounters = mockk()
        createCounter = mockk()
        context = mockk()
        viewModel = CounterViewModelImp(
            getCounters, update,deleteCounters,createCounter
        )
    }

    @Test
    fun fetchList() =  runBlockingTest {
        coEvery { getCounters.invoke(any()) } returns initLis()
        viewModel.fetchList()
        viewModel.fetchData.observeForever {
           assertNotNull(it)
        }

    }

    @Test
    fun fetchListNull() =  runBlockingTest {
        coEvery { getCounters.invoke(any()) } returns initLisNull()
        val expe =  mutableListOf<CountersDTO>()
        viewModel.fetchList()
        viewModel.fetchData.observeForever {
            assertEquals(expe, it)
        }
    }


    private fun initLis(): List<Counters> = mutableListOf(
        Counters(id = 1, title = "Cups of coffee", count = 0),
        CountersDTO(id = 2, title = "Cups of tea", count = 0),
        CountersDTO(id = 3, title = "Cups of coca", count = 0),
        CountersDTO(id = 4, title = "Cups of juice", count = 0)
    ) as List<Counters>

    private fun initLisNull(): List<Counters> = mutableListOf<Counters>()
}