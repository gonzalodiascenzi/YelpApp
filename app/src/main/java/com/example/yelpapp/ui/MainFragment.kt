package com.example.yelpapp.ui

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.yelpapp.R
import com.example.yelpapp.databinding.FragmentMainBinding
import com.example.yelpapp.model.YelpDbClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class MainFragment : Fragment(R.layout.fragment_main) {

    val DEFAULT_LATITUDE = -31.417
    val DEFAULT_LONGITUDE = -64.183

    private val adapter = BusinessesAdapter() {
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            requestLocalBusiness(isGranted)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        val binding = FragmentMainBinding.bind(view).run {
            recycler.adapter = adapter
        }

        requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    @SuppressLint("MissingPermission")
    private fun requestLocalBusiness(isLocationGranted: Boolean) {
        lifecycleScope.launch {
            val location = getLocation(isLocationGranted)
            val lat = location?.latitude ?: DEFAULT_LATITUDE
            val long = location?.longitude ?: DEFAULT_LONGITUDE
            val locationBusiness =
                YelpDbClient.service.searchBusinesses(lat.toString(), long.toString())
            adapter.submitList(locationBusiness.businesses)
        }
    }

    private suspend fun getLocation(granted: Boolean): Location? {
        return if (granted) findLastLocation() else null
    }

    @SuppressLint("MissingPermission")
    private suspend fun findLastLocation(): Location? =
        suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnCompleteListener {
                    continuation.resume(it.result)
                }
        }
}