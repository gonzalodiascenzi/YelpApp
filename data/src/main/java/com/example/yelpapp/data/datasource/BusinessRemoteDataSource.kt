package com.example.yelpapp.data.datasource


import arrow.core.Either
import com.example.yelpapp.domain.Business
import com.example.yelpapp.domain.Error

interface BusinessRemoteDataSource{
    suspend fun searchBusiness(region : Pair<String, String>) : Either<Error, List<Business>>

}