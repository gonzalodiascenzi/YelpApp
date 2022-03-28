package com.example.yelpapp.data.entity

data class YelpSearchResult(
    val businesses: List<Business>,
    val region: Region,
    val total: Int
)