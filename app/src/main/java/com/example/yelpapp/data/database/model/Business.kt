package com.example.yelpapp.data.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Business(
    @PrimaryKey
    val id: String,
    val alias: String,
//    @Embedded
//    val categories: List<Category>?,
    @Embedded
    val coordinates: Coordinates?,
    val display_phone: String,
    val distance: Double,
    val image_url: String,
    val is_closed: Boolean,
    @Embedded
    val location: Location?,
    val name: String,
    val phone: String,
    val price: String,
    val rating: Double,
    val review_count: Int,
    val transactions: List<String>?,
    val url: String
)

