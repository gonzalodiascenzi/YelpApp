package com.example.yelpapp.data.repository

import android.Manifest
import android.app.Application
import android.location.Location
import com.example.yelpapp.data.datasource.YelpDbClient
import com.example.yelpapp.data.entity.YelpSearchResult
import com.example.yelpapp.model.LocationDataSource
import com.example.yelpapp.data.PermissionChecker
import com.example.yelpapp.model.PlayServicesLocationDataSource

class BusinessRepository(app : Application) {

    private val DEFAULT_LATITUDE = -31.417
    private val DEFAULT_LONGITUDE = -64.183

    private val locationDataSource : LocationDataSource = PlayServicesLocationDataSource(app)

    private val coarsePermissionChecker = PermissionChecker(
        app,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    suspend fun searchBusiness() : YelpSearchResult {
        val location = findLastLocation()
        val lat = location?.latitude ?: DEFAULT_LATITUDE
        val long = location?.longitude ?: DEFAULT_LONGITUDE

        return YelpDbClient.service.searchBusinesses(lat.toString(), long.toString())
    }

    private suspend fun findLastLocation() : Location?{
        val success = coarsePermissionChecker.check()
        return if (success) locationDataSource.findLastLocation() else null
    }

}