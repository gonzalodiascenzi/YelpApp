package com.example.yelpapp.model

import retrofit2.http.GET
import retrofit2.http.Query

interface YelpDbService {

    @GET("/v3/businesses/search")
    fun getYelpSearch(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): YelpSearchResult

}




