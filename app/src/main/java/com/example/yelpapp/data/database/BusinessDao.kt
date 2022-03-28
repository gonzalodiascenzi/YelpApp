package com.example.yelpapp.data.database

import androidx.room.*
import com.example.yelpapp.data.database.model.Business
import kotlinx.coroutines.flow.Flow

@Dao
interface BusinessDao {

    @Query("SELECT * FROM Business")
    fun getAll(): Flow<List<Business>>

    @Query("SELECT * FROM Business WHERE id LIKE :id")
    fun findById(id: String): Flow<Business>

    @Query("SELECT COUNT(id) FROM Business")
    fun businessCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBusiness(business: List<Business>)

    @Update
    fun updateBusiness(business: Business)
}