package com.example.yelpapp.model
import com.google.gson.annotations.SerializedName

data class RemoteResult(
    val page: Int,
    val results: List<Business>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class Business(
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
    val price: String,
    val rating: Double,
    val review_count: Int,
    val transactions: List<String>,
    val url: String
)