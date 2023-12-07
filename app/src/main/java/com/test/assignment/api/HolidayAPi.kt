package com.test.assignment.api

import com.test.assignment.model.MainEnglandAndWales
import retrofit2.Response
import retrofit2.http.GET

interface HolidayAPi {
    @GET("/bank-holidays.json")
    suspend fun getHolidays() : Response<MainEnglandAndWales>
}