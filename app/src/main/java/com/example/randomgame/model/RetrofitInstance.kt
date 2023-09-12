package com.example.randomgame.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton object for managing Retrofit instance
object RetrofitInstance {
    private const val BASE_URL = "https://www.random.org/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Create an instance of the API interface using lazy initialization
    val api: RandomApi by lazy {
        retrofit.create(RandomApi::class.java)
    }
}