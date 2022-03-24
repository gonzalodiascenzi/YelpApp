package com.example.yelpapp.ui.main

import androidx.lifecycle.*
import com.example.yelpapp.model.Business
import com.example.yelpapp.model.BusinessRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val businessRepository: BusinessRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        onUiReady()
    }

    private fun onUiReady(){
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false,businesses = businessRepository.searchBusiness().businesses)
        }
    }

    fun onBusinessClicked(business: Business){
        _state.value = _state.value.copy(navigateTo = business)
    }

    fun onNavigationDone(){
        _state.value = _state.value.copy(navigateTo = null)
    }

    data class UiState(
        val loading: Boolean = false,
        val businesses: List<Business>? = null,
        val navigateTo: Business? = null,
        val requestLocationPermission: Boolean = true
    )

    fun onLocationPermissionChecked() {
        _state.value = _state.value.copy(requestLocationPermission = false)
        onUiReady()
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val businessRepository: BusinessRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(businessRepository) as T
    }
}