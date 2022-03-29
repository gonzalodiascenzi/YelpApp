package com.example.yelpapp.model

import com.devexperto.architectcoders.App
import com.example.yelpapp.R
import com.example.yelpapp.model.database.Business
import com.example.yelpapp.model.datasources.BusinessLocalDataSource
import com.example.yelpapp.model.datasources.BusinessRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.yelpapp.model.database.Business as RemoteBusiness

class BusinessRepository(application: App) {

    private val localDataSource = BusinessLocalDataSource(application.db.movieDao())
    private val remoteDataSource = BusinessRemoteDataSource(
        application.getString(R.string.latitude),
        RegionRepository(application)
    )

    val nearBusiness = localDataSource.business


    suspend fun requestBusinesses() = withContext(Dispatchers.IO){
        if (localDataSource.isEmpty()){
            val business = remoteDataSource.searchBusinesses()
            localDataSource.save(business.results.toLocalModel())
        }
    }

}

private fun List<RemoteBusiness>.toLocalModel(): List<Business> = map {it.toLocalModel()}

private fun RemoteBusiness.toLocalModel(): Business = Business(
id,
alias,
coordinates ?: "",
display_phone,
distance,
image_url,
is_closed,
location,
name,
phone,
price,
rating,
review_count,
transactions,
url
)
