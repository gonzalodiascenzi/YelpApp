package com.example.yelpapp.model


import com.google.gson.annotations.SerializedName

data class BusinessesResult(
    @SerializedName("businesses")
    val businesses: List<Business>,
    @SerializedName("region")
    val region: Region,
    @SerializedName("total")
    val total: Int
)