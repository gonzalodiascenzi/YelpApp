package com.example.yelpapp.model

import com.example.yelpapp.model.Business
import com.example.yelpapp.model.Region

data class YelpSearchResult(
    val businesses: List<Business>,
    val region: Region,
    val total: Int
)