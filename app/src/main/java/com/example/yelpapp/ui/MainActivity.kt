package com.example.yelpapp.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.example.yelpapp.R
import com.example.yelpapp.databinding.ActivityMainBinding
import com.example.yelpapp.model.Business
import com.example.yelpapp.model.Businesses
import com.example.yelpapp.model.YelpDbClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.nio.DoubleBuffer
import kotlin.concurrent.thread
import kotlin.coroutines.resume

class MainActivity : AppCompatActivity() {

    companion object{
        private const val DEFAULT_LONGITUDE = -34.61315
        private const val DEFAULT_LATITUDE = -58.37723
    }

    data class LatLong(var lat: Double = DEFAULT_LATITUDE, var long: Double = DEFAULT_LONGITUDE)

    private val businessAdapter = BusinessesAdapter{
        Toast.makeText(this,it.name, Toast.LENGTH_LONG).show()
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            requestLocalBusiness(isGranted)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.recycler.adapter = businessAdapter

        requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)

    }

    @SuppressLint("MissingPermission")
    private fun requestLocalBusiness(isLocationGranted: Boolean) {
        if (isLocationGranted) {
            fusedLocationClient.lastLocation.addOnCompleteListener {
                doRequestLocalBusiness(getLatlong(it.result))
            }
        } else {
            doRequestLocalBusiness(LatLong())
        }

    }

    private fun doRequestLocalBusiness(latlong: MainActivity.LatLong) {
        lifecycleScope.launch {
            val apiKey = getString(R.string.api_key)
            val locationBusiness =
                YelpDbClient.service.getYelpSearch(apiKey, "", latlong.lat, latlong.long)
            businessAdapter.submitList(locationBusiness.businesses)
            businessAdapter.notifyDataSetChanged()
        }

    }

    private fun getLatlong(location: Location?): LatLong {
        if (location == null) return LatLong()
        return LatLong(location.latitude, location.longitude)
    }

}