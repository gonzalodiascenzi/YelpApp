package com.example.yelpapp.data.datasource


import arrow.core.Either
import com.example.yelpapp.domain.Business


interface BusinessRemoteDataSource{
    suspend fun searchBusiness(region : String) : Either<Error, List<Business>>
            //List<Business> = service.searchBusinesses(lat,lng).businesses.map { it.toDomainModel() }
}