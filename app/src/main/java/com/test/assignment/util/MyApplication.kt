package com.test.assignment.util

import android.app.Application
import com.test.assignment.dagger.DaggerMyComponent
import com.test.assignment.dagger.MyComponent

class MyApplication:Application() {
    lateinit var application: MyComponent
    override fun onCreate() {
        super.onCreate()
application=DaggerMyComponent.builder().build()
    }
}