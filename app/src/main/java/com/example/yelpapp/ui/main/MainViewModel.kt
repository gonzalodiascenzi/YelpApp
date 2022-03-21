package com.example.yelpapp.ui.main

import androidx.lifecycle.*
import com.example.yelpapp.model.Business
import com.example.yelpapp.model.BusinessRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val businessRepository: BusinessRepository
) : ViewModel() {

    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> = _state

    private fun onUiReady(){
        viewModelScope.launch {
            _state.value = _state.value?.copy(loading = true)
            _state.value = _state.value?.copy(loading = false,businesses = businessRepository.searchBusiness().businesses)
        }
    }

    fun onBusinessClicked(business: Business){
        _state.value = _state.value?.copy(navigateTo = business)
    }

    data class UiState(
        val loading: Boolean = false,
        val businesses: List<Business>? = null,
        val navigateTo: Business? = null,
        val requestLocationPermission: Boolean = true
    )

    fun onLocationPermissionChecked() {
        _state.value = _state.value?.copy(requestLocationPermission = false)
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