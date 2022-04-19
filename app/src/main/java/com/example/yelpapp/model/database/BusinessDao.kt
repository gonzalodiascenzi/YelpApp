package com.example.yelpapp.model.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BusinessDao {

    @Query("SELECT * FROM Business")
    fun getAll(): Flow<List<Business>>

    @Query("SELECT * FROM Business WHERE id LIKE :id")
    fun findById(id: String): Flow<Business>

    @Query("SELECT COUNT(id) FROM Business")
    suspend fun businessCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBusiness(business: List<Business>)

    @Update
    fun updateBusiness(business: Business)
}