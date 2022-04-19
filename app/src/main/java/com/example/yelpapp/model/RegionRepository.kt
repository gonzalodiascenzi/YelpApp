package com.example.yelpapp.model

import android.Manifest
import android.location.Location
import com.example.yelpapp.App
import com.example.yelpapp.domain.Coordinates

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