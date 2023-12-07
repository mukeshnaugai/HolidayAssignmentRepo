package com.test.assignment.dagger

import com.test.assignment.api.HolidayAPi
import com.test.assignment.api.RetrofitHelper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitInstance {

    @Provides
    fun getRetrfitData(): Retrofit {
        return Retrofit.Builder().baseUrl(RetrofitHelper.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }

    @Provides
    fun getHolidayApi(retrofit: Retrofit): HolidayAPi {
        return retrofit.create(HolidayAPi::class.java)
    }
}