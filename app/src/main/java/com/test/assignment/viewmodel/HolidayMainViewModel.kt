package com.test.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.assignment.api.NetworkRequest
import com.test.assignment.repository.EnglandHolidayRepo
import com.test.assignment.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HolidayMainViewModel(private val quotesRepo: EnglandHolidayRepo) : ViewModel() {
  private  val _holiday=MutableLiveData<NetworkRequest<List<Event>>>()
    val liveData: LiveData<NetworkRequest<List<Event>>>

        get() = _holiday

    fun getHolidayDetails(){
        viewModelScope.launch(Dispatchers.IO) {
           val result= quotesRepo.getHolidayDetails()
            _holiday.postValue( result)
        }

    }


}