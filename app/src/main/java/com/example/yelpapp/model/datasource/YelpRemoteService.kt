package com.example.yelpapp.model.datasource


import com.example.yelpapp.model.YelpSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface YelpRemoteService {

        @GET("/v3/businesses/search")
        fun getYelpSearch(
            @Query("term") term: String,
            @Query("latitude") latitude: Double,
            @Query("longitude") longitude: Double
        ): YelpSearchResult
    }




