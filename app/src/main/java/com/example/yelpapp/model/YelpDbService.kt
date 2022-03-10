package com.example.yelpapp.model


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.Flow

interface YelpDbService {



        @GET("/v3/businesses/search")
        fun getYelpSearch(
            @Query("term") term: String,
            @Query("latitude") latitude: Double,
            @Query("longitude") longitude: Double
        ): YelpSearchResult
    }




