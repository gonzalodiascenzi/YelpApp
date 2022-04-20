package com.example.yelpapp.model

import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.example.yelpapp.App
import com.example.yelpapp.model.database.Coordinates
import com.example.yelpapp.model.database.Location
import com.example.yelpapp.model.datasource.LocalDataSource
import com.example.yelpapp.model.datasource.RemoteDataSource

class BusinessRepository(app : App) {

    private val DEFAULT_LATITUDE = -31.417
    private val DEFAULT_LONGITUDE = -64.183

    private val regionRepository : RegionRepository = RegionRepository(app)
    private val localDataSource : LocalDataSource = LocalDataSource(app.db.businessDao())
    private val remoteDataSource : RemoteDataSource = RemoteDataSource(YelpDbClient.service)

    val business = localDataSource.business

    suspend fun requestBusiness() {
        if(localDataSource.isEmpty()){
            val location = regionRepository.findLastCoordinates()
            val lat = location?.latitude ?: DEFAULT_LATITUDE
            val long = location?.longitude ?: DEFAULT_LONGITUDE

            val business = remoteDataSource.searchBusiness(lat.toString(),long.toString())
            localDataSource.save(business)
        }
    }
    suspend fun switchFavorite(business: Business) {
        val updatedBusiness = business.copy(favorite = !business.favorite)
        localDataSource.save(listOf(updatedBusiness))
    }

    private fun List<RemoteDataSource>.toLocalModel(): List<Business> = map { it.toLocalMovie() }

}
    private fun RemoteDataSource.toLocalModel(): Business = Business(
    id: String,
    alias: String,
    favorite: Boolean,
    coordinates: Coordinates?,
    display_phone: String,
    distance: Double,
    image_url: String,
    is_closed: Boolean,
    location: Location?,
    name: String,
    phone: String,
    price: String,
    rating: Double,
    review_count: Int,
    transactions: List<String>?,
    url: String,
    false
    )

