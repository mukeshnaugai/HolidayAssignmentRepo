package com.test.assignment.api

sealed class NetworkRequest<T>(val data:T?=null,val string: String?=null){
    class Success<T>(data: T?): NetworkRequest<T>(data)
    class Error<T>(message:String?=null,data: T?=null): NetworkRequest<T>(data,message)
    class Loading<T>(): NetworkRequest<T>()


}



