package com.example.yelpapp.framework.datasource

import android.annotation.SuppressLint
import android.app.Application
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import android.location.Location
import com.example.yelpapp.data.datasource.LocationDataSource
import kotlin.coroutines.resume

class PlayServicesLocationDataSource(app : Application) : LocationDataSource {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(app)

    @SuppressLint("MissingPermission")
    override suspend fun findLastLocation(): Location? =
        suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnCompleteListener {
                    continuation.resume(it.result)
                }
        }
}