package com.example.yelpapp.framework.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey
    val id: Int,
    val alias: String,
    val title: String
)
