package com.olekslukian.simplenotes.core

sealed class Result<T>(val data:  T?, val message : String?=null){
    class Success<T> (data: T?) : Result<T>(data)
    class Loading<T>(data: T?= null) : Result<T>(data)
    class Error<T>(data: T? = null, message: String?) : Result<T>(data,message)
}