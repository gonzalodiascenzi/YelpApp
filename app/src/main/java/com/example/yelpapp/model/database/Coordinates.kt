package com.example.yelpapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity
data class Coordinates(
//    @PrimaryKey
    val id: Int,
    val latitude: Double,
    val longitude: Double
)