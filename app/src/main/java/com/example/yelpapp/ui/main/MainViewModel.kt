package com.example.yelpapp.ui.main

import androidx.lifecycle.*
<<<<<<< HEAD
import com.example.yelpapp.data.entity.Business
import com.example.yelpapp.data.repository.BusinessRepository
=======
import com.example.yelpapp.domain.Business
import com.example.yelpapp.model.BusinessRepository
>>>>>>> a5eeb09a5fc5cb26d7a2fd7dbaccd526b34624e0
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val businessRepository: BusinessRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun onUiReady(){
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false,businesses = businessRepository.searchBusiness())
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