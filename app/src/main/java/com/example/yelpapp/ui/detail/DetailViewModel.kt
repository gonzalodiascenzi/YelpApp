package com.example.yelpapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.yelpapp.domain.Business
import com.example.yelpapp.usecases.GetBusinessByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getBusinessByIdUseCase: GetBusinessByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        //_state.update { UiState(business = business) }
    }

    data class UiState(
        val loading: Boolean = false,
        val business: Business? = null
    )

    fun onUiReady(business: Business?
        //id:  String
    ){
        viewModelScope.launch {
            business?.let {
                _state.update { UiState(business = business) }
            }
            /*getBusinessByIdUseCase.invoke(id)
                ?.collect { bussiness -> _state.update { UiState(business = bussiness) }  }*/
        }
    }
}

