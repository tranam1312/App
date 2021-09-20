package com.example.app.Api



import com.example.app.model.User

import retrofit2.http.GET
import retrofit2.http.Query


interface SeverApi {
    @GET("character")
    suspend fun getAll(@Query ("page") page : Int) : User

}