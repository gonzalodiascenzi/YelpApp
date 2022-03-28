package com.example.yelpapp.data.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey
    val id: Int,
    val alias: String,
    val title: String
)
