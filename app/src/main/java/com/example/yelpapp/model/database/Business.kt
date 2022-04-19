package com.example.yelpapp.model.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yelpapp.model.Category
import com.example.yelpapp.domain.Business as DomainModel

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

fun Business.toDomainModel() = DomainModel(alias,display_phone,distance,id,image_url,is_closed, location?.address1 ?: "",location?.city ?: "",name,phone,price,rating,review_count,transactions ?: emptyList(),url)

fun DomainModel.fromDomainModel() = Business(id,alias,null,display_phone,distance,image_url,is_closed,null,name, phone, price, rating, review_count, transactions, url)