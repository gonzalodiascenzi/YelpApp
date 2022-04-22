package com.example.yelpapp.data

import android.Manifest
import android.location.Location
import com.example.yelpapp.App
import com.example.yelpapp.domain.Coordinates
import com.example.yelpapp.data.datasource.LocationDataSource
import com.example.yelpapp.framework.datasource.PlayServicesLocationDataSource

class RegionRepository(app : App) {

    private val locationDataSource: LocationDataSource = PlayServicesLocationDataSource(app)
    private val coarsePermissionChecker = PermissionChecker(
        app,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    suspend fun findLastCoordinates(): Coordinates? = findLastLocation()?.toCoordinates()

    private suspend fun Location.toCoordinates() = Coordinates(latitude,longitude)

    private suspend fun findLastLocation(): Location? {
        val success = coarsePermissionChecker.check()
        return if (success) locationDataSource.findLastLocation() else null
    }
}