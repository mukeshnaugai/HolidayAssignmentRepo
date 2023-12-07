package com.test.assignment.MainViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.assignment.viewmodel.HolidayMainViewModel
import com.test.assignment.repository.EnglandHolidayRepo
import javax.inject.Inject

class MainFactory @Inject constructor(private val repo: EnglandHolidayRepo) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HolidayMainViewModel(repo) as T
    }
}