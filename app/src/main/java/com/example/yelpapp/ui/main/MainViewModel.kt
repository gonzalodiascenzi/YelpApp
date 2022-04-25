package com.example.yelpapp.ui.main

import androidx.lifecycle.*
import com.example.yelpapp.data.repository.BusinessRepository
import com.example.yelpapp.domain.Business
import com.example.yelpapp.usecases.GetBusinessUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val businessRepository: BusinessRepository,
    private val getBusinessUseCase: GetBusinessUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()


    init {
        viewModelScope.launch {
            businessRepository.business
                .collect { businesses -> _state.value = UiState(businesses = businesses)
                }
        }
    }

    fun onUiReady(){
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            businessRepository.requestBusiness()
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val businesses: List<Business>? = null,
    )
}
