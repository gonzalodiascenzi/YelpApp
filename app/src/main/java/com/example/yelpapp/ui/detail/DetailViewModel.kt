package com.example.yelpapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.yelpapp.domain.Business

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val business: Business
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        _state.update { UiState(business = business) }
    }

    data class UiState(
        val loading: Boolean = false,
        val business: Business? = null
    )
}

fun onFavoriteClicked() {
    viewModelScope.launch {
        _state.value.business?.let { repository.switchFavorite(it) }
    }
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val business: Business) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(business) as T
    }
}