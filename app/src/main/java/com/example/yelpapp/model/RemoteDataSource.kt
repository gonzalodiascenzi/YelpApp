package com.example.yelpapp.model

import com.example.yelpapp.domain.Business

class RemoteDataSource(
    private val service : YelpDbService
) {
    suspend fun searchBusiness(lat : String, lng : String) : List<Business> = service.searchBusinesses(lat,lng).businesses.map { it.toDomainModel() }
}