package com.example.app.Api



import com.example.app.model.User
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Query


interface SeverApi {
    @GET("character")
    fun getAll(@Query ("page") page : Int) : Single<User>

    }
