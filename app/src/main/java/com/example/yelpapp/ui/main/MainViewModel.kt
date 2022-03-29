package com.example.yelpapp.ui.main

import androidx.lifecycle.*
import com.example.yelpapp.model.BusinessRepository
import com.example.yelpapp.model.database.Business
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
        viewModelScope.launch {
            businessRepository.nearBusiness.collect{ business ->
                _state.value = UiState(businesses = business)
            }
        }
    }

    fun onUiReady(){
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            businessRepository.requestBusinesses()
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val businesses: List<Business>? = null,
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val businessRepository: BusinessRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(businessRepository) as T
    }
}