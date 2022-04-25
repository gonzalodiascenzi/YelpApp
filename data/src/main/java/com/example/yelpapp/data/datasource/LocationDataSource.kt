package com.example.yelpapp.data.server



interface LocationDataSource {
    suspend fun findLastLocation(): String?
}

