package com.example.yelpapp.model

data class YelpSearchResult(
    val businesses: List<Business>,
    val region: Region,
    val total: Int
)