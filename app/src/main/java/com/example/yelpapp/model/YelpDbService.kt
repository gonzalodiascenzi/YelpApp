package com.example.yelpapp.model


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.concurrent.Flow

interface YelpDbService {



        @GET("/v3/businesses/search")
        suspend fun getYelpSearch(
            @Header("Authorization") authHeader: String,
            @Query("term") term: String,
            @Query("latitude") latitude: Double,
            @Query("longitude") longitude: Double
        ): YelpSearchResult
    }




