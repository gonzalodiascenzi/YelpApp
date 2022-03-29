package com.example.yelpapp.model.datasources

import com.example.yelpapp.model.RegionRepository
import com.example.yelpapp.model.RemoteConnection


class BusinessRemoteDataSource(
    private val latitude: String,
    private val longitude: RegionRepository
){
    suspend fun searchBusinesses() = RemoteConnection.service
}