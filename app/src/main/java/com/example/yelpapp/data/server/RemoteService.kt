package com.example.yelpapp.data.server

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("businesses/search")
    suspend fun searchBusinesses(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String
    ): RemoteResult
}