package com.example.yelpapp.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object YelpDbClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.yelp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(YelpDbService::class.java)

}