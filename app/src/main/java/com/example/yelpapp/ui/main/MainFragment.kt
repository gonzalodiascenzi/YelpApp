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
import com.example.yelpapp.ui.common.launchAndCollect
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(BusinessRepository(requireActivity().application)) }

    private val adapter = BusinessesAdapter { mainState.onBusinessClicked(it)}

    private lateinit var mainState: MainState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainState = buildMainState()

        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state) {binding.updateUI(it)}

        mainState.requestLocationPermission { viewModel.onUiReady() }

    }

    private fun FragmentMainBinding.updateUI(state: MainViewModel.UiState) {
        progress.isVisible = state.loading
        state.businesses?.let(adapter::submitList)

    }


}