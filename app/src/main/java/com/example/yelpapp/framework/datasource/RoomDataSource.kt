package com.example.yelpapp.framework.datasource

import com.example.yelpapp.data.datasource.LocalDataSource
import com.example.yelpapp.framework.database.BusinessDao
import kotlinx.coroutines.flow.Flow
import com.example.yelpapp.domain.Business
import com.example.yelpapp.framework.database.fromDomainModel
import com.example.yelpapp.framework.database.toDomainModel
import kotlinx.coroutines.flow.map

class RoomDataSource(
    private val businessDao: BusinessDao
) : LocalDataSource {

    override val business: Flow<List<Business>> = businessDao.getAll().map { it.map { it.toDomainModel() } }

    override suspend fun isEmpty(): Boolean = businessDao.businessCount() == 0

    override fun findById(id: String): Flow<Business> = businessDao.findById(id).map { it.toDomainModel() }

    override suspend fun save(business: List<Business>) {
        businessDao.insertBusiness(business.map { it.fromDomainModel() })
    }
}