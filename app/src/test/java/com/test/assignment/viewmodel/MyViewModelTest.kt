package com.test.assignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.assignment.api.NetworkRequest
import com.test.assignment.repository.EnglandHolidayRepo
import com.test.assignment.getOrAwaitValue
import com.test.assignment.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MyViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    @Mock
    lateinit var englandHolidayRepo: EnglandHolidayRepo


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

    }

    @Test
    fun get_products_empty_list() = runTest {
        Mockito.`when`(englandHolidayRepo.getHolidayDetails())
            .thenReturn(NetworkRequest.Success(emptyList()))
        val sut = HolidayMainViewModel(englandHolidayRepo)
        sut.getHolidayDetails()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.liveData.getOrAwaitValue()
        Assert.assertEquals(0, result.data!!.size)


    }

    @Test
    fun get_products_error() = runTest {
        Mockito.`when`(englandHolidayRepo.getHolidayDetails())
            .thenReturn(NetworkRequest.Error("Something went wrong"))
        val sut = HolidayMainViewModel(englandHolidayRepo)
        sut.getHolidayDetails()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.liveData.getOrAwaitValue()
        Assert.assertEquals(true, result is NetworkRequest.Error)
        Assert.assertEquals("Something went wrong", result.string)
    }

    @Test
    fun get_all_product_list() = runTest {
        val list= listOf<Event>(
            Event("Christmas","25-12,23",true)
        , Event("New Year","1-1-24",true)
        )

        Mockito.`when`(englandHolidayRepo.getHolidayDetails())
            .thenReturn(NetworkRequest.Success(list))
        val sut = HolidayMainViewModel(englandHolidayRepo)
        sut.getHolidayDetails()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.liveData.getOrAwaitValue()
        Assert.assertEquals(true, result is NetworkRequest.Success)
        Assert.assertEquals(2, result.data!!.size)


    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}