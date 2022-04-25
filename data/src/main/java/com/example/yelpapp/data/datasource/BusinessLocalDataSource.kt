package com.example.yelpapp.data.datasource


import com.example.yelpapp.domain.Business
import kotlinx.coroutines.flow.Flow
import com.example.yelpapp.domain.Error

interface BusinessLocalDataSource {
    val business: Flow<List<Business>>

    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<Business>
    suspend fun save(movies: List<Business>): Error?
}