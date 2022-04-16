package com.example.yelpapp.data.datasource

import com.example.yelpapp.data.entity.YelpSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface YelpDbService {
    @GET("businesses/search")
    suspend fun searchBusinesses(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String
    ): YelpSearchResult
}