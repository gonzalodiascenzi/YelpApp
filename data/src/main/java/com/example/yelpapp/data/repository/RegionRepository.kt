package com.example.yelpapp.data.repository


import com.example.yelpapp.data.PermissionChecker


import com.example.yelpapp.domain.Coordinates
import com.example.yelpapp.model.LocationDataSource
import javax.inject.Inject

class RegionRepository @Inject constructor(
    private val locationDataSource: LocationDataSource,
    private val permissionChecker : PermissionChecker
) {

    companion object {
        const val DEFAULT_REGION = "US"
    }
   /* suspend fun findLastCoordinates(): Coordinates? = findLastLocation()?.toCoordinates()

    private suspend fun Location.toCoordinates() = Coordinates(latitude,longitude)*/

    private suspend fun findLastLocation(): Location? {
       /* val success = coarsePermissionChecker.check()
        return if (success) locationDataSource.findLastLocation() else null*/
        return if (permissionChecker.check(COARSE_LOCATION)) {
            locationDataSource.findLastLocation() ?: DEFAULT_REGION
        } else {
            DEFAULT_REGION
        }

    }
}