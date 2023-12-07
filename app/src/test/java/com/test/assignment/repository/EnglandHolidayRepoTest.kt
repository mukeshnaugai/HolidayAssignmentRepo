package com.test.assignment.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.assignment.api.HolidayAPi
import com.test.assignment.api.NetworkRequest
import com.test.assignment.model.MainEnglandAndWales
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class EnglandHolidayRepoTest {
    @Mock
    lateinit var holidayAPi: HolidayAPi

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

    }

    @Test
    fun is_response_valid()= runTest {
        Mockito.`when`(holidayAPi.getHolidays()).thenReturn(Response.success(MainEnglandAndWales()))
        val sut = EnglandHolidayRepo(holidayAPi)
       val k= sut.getMainData ()
        Assert.assertEquals(true,k is NetworkRequest.Success)
    }

    @Test
    fun is_response_not_valid()= runTest {
        Mockito.`when`(holidayAPi.getHolidays()).thenReturn(Response.error(401,"Unautohorized".toResponseBody()))
        val sut = EnglandHolidayRepo(holidayAPi)
        val result= sut.getMainData()
        Assert.assertEquals(true,result is NetworkRequest.Error)
        Assert.assertEquals("Something went wrong", result.string)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()

    }
}