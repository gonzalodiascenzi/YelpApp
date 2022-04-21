import androidx.lifecycle.*
import com.example.yelpapp.domain.Business
import com.example.yelpapp.model.BusinessRepository
import com.example.yelpapp.model.Error
import com.example.yelpapp.model.toError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*

class MainViewModel(
    private val businessRepository: BusinessRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            businessRepository.business
                .catch {cause -> _state.update { it.copy(error =  cause.toError()) }}
                .collect { businesses -> _state.value = UiState(businesses = businesses)
                }
        }
    }

    fun onUiReady(){
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            val error =    businessRepository.requestBusiness()
            _state.update { _state.value.copy(loading = false, error = error) }

        }
    }

    data class UiState(
        val loading: Boolean = false,
        val businesses: List<Business>? = null,
        val error : Error? = null
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val businessRepository: BusinessRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(businessRepository) as T
    }
}