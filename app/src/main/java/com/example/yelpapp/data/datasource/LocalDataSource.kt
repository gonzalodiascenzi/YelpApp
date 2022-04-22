package com.example.yelpapp.data.datasource

import com.example.yelpapp.domain.Business
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    val business: Flow<List<Business>>

    suspend fun isEmpty(): Boolean
    fun findById(id: String): Flow<Business>
    suspend fun save(business: List<Business>)
}