package com.example.yelpapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface YelpDbService {
    @GET("businesses/search")
    suspend fun searchBusinesses(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String
    ): YelpSearchResult
}