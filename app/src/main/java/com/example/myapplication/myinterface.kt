package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface myinterface {
    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String,@Query("apiKey") apiKey: String): Call<allData>
}