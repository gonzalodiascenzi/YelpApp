package com.example.yelpapp.model

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object YelpDbClient {
    private const val token = "R1R9DOL09jglEGD1X_uu0nwvLAd0JhiLujEZqFfa9YwJjroOHFI_PsHiUFUwkDrlyMq4PHmOouElxOCMgaOlhEa2Cymwl4k2LU09ytvNMomlwbd0sQCeuINLcMQWYnYx"

    private val tokenInterceptor = Interceptor { chain ->
        chain.proceed(
            chain.request().newBuilder()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer $token")
                .build()
        )
    }

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(this)
            .addInterceptor(tokenInterceptor)
            .build()
    }

    private val builder = Retrofit.Builder()
        .baseUrl("https://api.yelp.com/v3/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: YelpDbService = builder.create()
}