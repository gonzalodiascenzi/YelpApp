package com.example.yelpapp.ui.main

import android.Manifest
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.yelpapp.R
import com.example.yelpapp.domain.Business
import com.example.yelpapp.ui.common.PermissionRequester
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.yelpapp.model.Error

fun Fragment.buildMainState(
    context: Context = requireContext(),

    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController(),
    locationPermissionRequester: PermissionRequester = PermissionRequester(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
) = MainState(context,scope, navController, locationPermissionRequester)

class MainState(
    private val context: Context,

    private val scope: CoroutineScope,
    private val navController: NavController,
    private val locationPermissionRequester: PermissionRequester
) {

    fun onBusinessClicked(business: Business) {
        val action = MainFragmentDirections.actionMainToDetail(business)
        navController.navigate(action)
    }

    fun requestLocationPermission(afterRequest: (Boolean) -> Unit) {
        scope.launch {
            val result = locationPermissionRequester.request()
            afterRequest(result)
        }
    }

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }
}