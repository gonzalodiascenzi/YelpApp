package com.example.yelpapp.data.repository


import com.example.yelpapp.data.PermissionChecker
import com.example.yelpapp.data.server.LocationDataSource


import javax.inject.Inject

class RegionRepository @Inject constructor(
    private val locationDataSource: LocationDataSource,
    private val permissionChecker : PermissionChecker
) {

    companion object {
        val DEFAULT_REGION = Pair("","")
    }

    suspend fun findLastLocation(): Pair<String, String> {
        return if (permissionChecker.check(PermissionChecker.Permission.COARSE_LOCATION)) {
            locationDataSource.findLastLocation()
        } else {
            DEFAULT_REGION
        }

    }
}