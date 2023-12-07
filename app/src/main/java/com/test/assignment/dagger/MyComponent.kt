package com.test.assignment.dagger

import com.test.assignment.MainActivity
import dagger.Component

@Component(modules = [RetrofitInstance::class])
interface MyComponent {
    fun inject(activity: MainActivity)

}