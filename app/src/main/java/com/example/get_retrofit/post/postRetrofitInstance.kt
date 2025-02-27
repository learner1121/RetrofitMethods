package com.example.get_retrofit.post

import com.example.get_retrofit.post.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object postRetrofitInstance {

    private const val BaseUrl = "https://jsonplaceholder.typicode.com/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}