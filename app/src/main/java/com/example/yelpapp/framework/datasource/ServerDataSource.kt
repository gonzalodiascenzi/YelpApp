package com.example.yelpapp.framework.datasource

import com.example.yelpapp.domain.Business
import com.example.yelpapp.data.YelpDbService
import com.example.yelpapp.data.datasource.RemoteDataSource
import com.example.yelpapp.data.toDomainModel

class ServerDataSource(
    private val service : YelpDbService
) : RemoteDataSource {
    override suspend fun searchBusiness(lat : String, lng : String) : List<Business> = service.searchBusinesses(lat,lng).businesses.map { it.toDomainModel() }
}