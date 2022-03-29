package com.example.yelpapp.model

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    @GET("businesses/search")
    suspend fun searchBusinesses(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String
    ): YelpSearchResult
}




