package com.example.yelpapp.model.datasource

import com.example.yelpapp.model.database.BusinessDao
import kotlinx.coroutines.flow.Flow
import com.example.yelpapp.domain.Business
import com.example.yelpapp.model.database.fromDomainModel
import com.example.yelpapp.model.database.toDomainModel
import kotlinx.coroutines.flow.map

class LocalDataSource(
    private val businessDao: BusinessDao
) {

    val business: Flow<List<Business>> = businessDao.getAll().map { it.map { it.toDomainModel() } }

    suspend fun isEmpty(): Boolean = businessDao.businessCount() == 0

    fun findById(id: String): Flow<Business> = businessDao.findById(id).map { it.toDomainModel() }

    suspend fun save(business: List<Business>) {
        businessDao.insertBusiness(business.map { it.fromDomainModel() })
    }
}