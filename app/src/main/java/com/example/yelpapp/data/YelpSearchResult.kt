package com.example.yelpapp.data

data class YelpSearchResult(
    val businesses: List<Business>,
    val region: Region,
    val total: Int
)