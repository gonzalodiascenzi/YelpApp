package com.example.yelpapp.model


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YelpDbService {



        @GET("/v3/businesses/search")
        fun getYelpSearch(
            @Header("Authorization") api: String,
            @Query("term") term: String,
            @Query("latitude") latitude: Double,
            @Query("longitude") longitude: Double
        ): Call<Yelp>
    }




