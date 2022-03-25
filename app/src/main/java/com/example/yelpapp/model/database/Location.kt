package com.example.yelpapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity
data class Location(
//    @PrimaryKey
    val address1: String?,
    val address2: String?,
    val address3: String?,
    val city: String?,
    val country: String?,
    val display_address: List<String>,
    val state: String?,
    val zip_code: String?,
)