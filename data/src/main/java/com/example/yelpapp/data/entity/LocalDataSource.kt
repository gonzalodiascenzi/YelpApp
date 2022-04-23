package com.example.yelpapp.model


import com.example.yelpapp.data.database.BusinessDao
import com.example.yelpapp.data.database.model.fromDomainModel
import com.example.yelpapp.data.database.model.toDomainModel
import kotlinx.coroutines.flow.Flow
import com.example.yelpapp.domain.Business
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