package com.example.cryptocurrencyy.common

sealed class Resource <T> (val data :T?= null,val message :String? = null){
    class Sucsses <T> (data: T) : Resource<T>(data)
    class Error <T> (message :String , data :T? = null):Resource<T>(data,message)
    class Loading <T>(data: T? = null) : Resource<T>(data)

}