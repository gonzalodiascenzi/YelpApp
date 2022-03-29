package com.example.yelpapp.model.datasources

import com.example.yelpapp.model.database.Business
import com.example.yelpapp.model.database.BusinessDao
import kotlinx.coroutines.flow.Flow


class BusinessLocalDataSource(private val businessDao: BusinessDao){

    val business: Flow<List<Business>> = businessDao.getAll()

    fun isEmpty(): Boolean = businessDao.businessCount() == 0

    fun save(business: List<Business>){
        businessDao.insertBusiness(business)
    }


}