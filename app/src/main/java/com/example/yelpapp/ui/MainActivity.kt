package com.example.yelpapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.example.yelpapp.R
import com.example.yelpapp.databinding.ActivityMainBinding
import com.example.yelpapp.model.Businesses
import com.example.yelpapp.model.YelpDbClient
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class MainActivity : AppCompatActivity() {

    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            lifecycleScope.launch {
                val location = getLocation(isGranted)
//                val movies = RemoteConnection.service.listPopularMovies(
//                    getString(R.string.api_key),
//                    getRegionFromLocation(location)
//                )
//                adapter.movies = movies.results
            }
        }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val adapter = BusinessesAdapter{
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = adapter
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

    companion object{
        private const val DEFAULT_REGION = "US"
    }

    private fun getRegionFromLocation(location: Location?): String {
        if (location==null) return DEFAULT_REGION
        val geocoder = Geocoder(this)
        val result = geocoder.getFromLocation(
            location.latitude,
            location.longitude,
            1
        )
        return result.firstOrNull()?.countryCode ?: DEFAULT_REGION
    }

    private fun doRequestPopularMovies(region: String) {
        lifecycleScope.launch { //UTILIZAMOS RETROFIT CON CORRUTINAS PARA RECUPERAR LOS DATOS DE LA API
            val apiKey = getString(R.string.api_key)
//            val popularMovies = YelpDbClient.service.listPopularMovies(apiKey, region)
//            adapter.movies = popularMovies.results
            adapter.notifyDataSetChanged()
        }
    }
}