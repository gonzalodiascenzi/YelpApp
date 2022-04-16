package com.example.yelpapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Business(
    val alias: String,
    val display_phone: String,
    val distance: Double,
    val id: String,
    val image_url: String,
    val is_closed: Boolean,
    val address: String,
    val city : String,
    val name: String,
    val phone: String,
    val price: String,
    val rating: Double,
    val review_count: Int,
    val transactions: List<String>,
    val url: String
) : Parcelable