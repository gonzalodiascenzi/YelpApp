package com.example.yelpapp.data.entity

import com.example.yelpapp.domain.Business as DomainModel

data class RemoteBusiness(
    val alias: String,
    val categories: List<Category>,
    val coordinates: Coordinates,
    val display_phone: String,
    val distance: Double,
    val id: String,
    val image_url: String,
    val is_closed: Boolean,
    val location: Location,
    val name: String,
    val phone: String,
    val price: String?,
    val rating: Double?,
    val review_count: Int,
    val transactions: List<String>,
    val url: String,
    val city: CharSequence?,
    val address: CharSequence?
)

fun RemoteBusiness.toDomainModel() : DomainModel{
    return DomainModel(alias,display_phone,distance,id,image_url,is_closed,location.address1,location.city,name,phone,
        price ?: "",
        rating ?: 0.0,
        review_count,transactions,url)
}