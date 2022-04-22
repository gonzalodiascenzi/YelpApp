package com.example.yelpapp.data.datasource

import com.example.yelpapp.domain.Business

interface RemoteDataSource {
    suspend fun searchBusiness(lat : String, lng : String) : List<Business>
}