package com.example.yelpapp.data.server

import com.example.yelpapp.domain.Coordinates


interface LocationDataSource {
    suspend fun findLastLocation(): Coordinates?
}

