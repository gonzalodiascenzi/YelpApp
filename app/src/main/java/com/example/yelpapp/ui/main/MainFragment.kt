package com.example.yelpapp.ui.main

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.yelpapp.R
import com.example.yelpapp.databinding.FragmentMainBinding
import com.example.yelpapp.model.Business
import com.example.yelpapp.model.BusinessRepository
import com.example.yelpapp.ui.common.PermissionRequester
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding : FragmentMainBinding
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(BusinessRepository(requireActivity().application)) }

    private val adapter = BusinessesAdapter(){
        viewModel.onBusinessClicked(it)
    }

    private val coarsePermissionRequester = PermissionRequester(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        viewModel.state.observe(viewLifecycleOwner, ::updateUI)
    }

    private fun updateUI(state: MainViewModel.UiState) {
        binding.progress.isVisible = state.loading
        state.businesses?.let(adapter::submitList)
        state.navigateTo?.let(::navigateTo)

        if (state.requestLocationPermission) {
            requestLocationPermission()
        }
    }

    private fun navigateTo(business: Business) {
        val action = MainFragmentDirections.actionMainToDetail()
        findNavController().navigate(action)
    }

    private fun requestLocationPermission() {
        viewLifecycleOwner.lifecycleScope.launch {
            coarsePermissionRequester.request()
            viewModel.onLocationPermissionChecked()
        }
    }
}