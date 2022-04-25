package com.example.yelpapp.data

import android.annotation.SuppressLint
import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.example.yelpapp.data.server.LocationDataSource

import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class PlayServicesLocationDataSource @Inject constructor(application: Application) :
    LocationDataSource {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
    private val geocoder = Geocoder(application)

    @SuppressLint("MissingPermission")
    override suspend fun findLastLocation(): Pair<String, String> =
        suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnCompleteListener {
                    continuation.resume(it.result.toRegion())
                }
        }

    private fun Location?.toRegion(): Pair<String, String> {
        val addresses = this?.let {
            geocoder.getFromLocation(latitude, longitude, 1)
        }
        return Pair(addresses?.get(0)?.latitude.toString(), addresses?.get(0)?.latitude.toString())
    }
}
