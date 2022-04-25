package com.example.yelpapp.data.server

import com.example.yelpapp.data.entity.Business
import com.example.yelpapp.data.entity.Region

data class RemoteResult(
    val businesses: List<Business>,
    val region: Region,
    val total: Int
)