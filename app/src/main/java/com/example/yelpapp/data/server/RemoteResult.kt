package com.example.yelpapp.data.server

import com.example.yelpapp.data.entity.RemoteBusiness
import com.example.yelpapp.data.entity.Region

data class RemoteResult(
    val businesses: List<RemoteBusiness>,
    val region: Region,
    val total: Int
)