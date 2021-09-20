package com.example.app.Api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SeverBuild {
    companion object {
        val gson = GsonBuilder().setLenient().create()
        fun instance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
    }
}