package com.example.yelpapp.model.datasource

import com.example.yelpapp.domain.Business
import com.example.yelpapp.model.YelpDbService
import com.example.yelpapp.model.toDomainModel

class RemoteDataSource(
    private val service : YelpDbService
) {
    suspend fun searchBusiness(lat : String, lng : String) : List<Business> = service.searchBusinesses(lat,lng).businesses.map { it.toDomainModel() }
}