package com.test.assignment.repository

import com.test.assignment.api.HolidayAPi
import com.test.assignment.api.NetworkRequest
import com.test.assignment.model.Event
import com.test.assignment.model.MainEnglandAndWales
import javax.inject.Inject

class EnglandHolidayRepo @Inject constructor(private val holidayAPi: HolidayAPi) {
    suspend fun getHolidayDetails(): NetworkRequest<List<Event>> {
        val holidayAPi = holidayAPi.getHolidays()
        if(holidayAPi.isSuccessful){
            return if (holidayAPi.body()!=null){
                NetworkRequest.Success(holidayAPi.body()!!.englandAndWales!!.events)
            } else{
                NetworkRequest.Error("Something went wrong")
            }

        }
return NetworkRequest.Error("Something went wrong")

    }
    suspend fun getMainData(): NetworkRequest<MainEnglandAndWales> {
        val holidayAPi = holidayAPi.getHolidays()
        if(holidayAPi.isSuccessful){
            return if (holidayAPi.body()!=null){
                NetworkRequest.Success(holidayAPi.body())
            } else{
                NetworkRequest.Error("Something went wrong")
            }

        }
        return NetworkRequest.Error("Something went wrong")

    }
}