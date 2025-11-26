import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.SampleDataProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface DetailViewModel {
    val sampleDataFlow: Flow<DetailUI?>
}

data class DetailUI(val name: String, val description: String)

//@Inject
class DetailViewModelImpl(
    sampleDataProvider: SampleDataProvider,
    val selectedItem: Int
): ViewModel(), DetailViewModel {

    class Factory(val sampleDataProvider: SampleDataProvider,
                  val selectedItem: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailViewModelImpl(sampleDataProvider, selectedItem) as T
        }
    }

    private val _sampleDataFlow: MutableStateFlow<DetailUI?> = MutableStateFlow(null)
    override val sampleDataFlow: Flow<DetailUI?>
        get() = _sampleDataFlow

    init {
        viewModelScope.launch {
            val result = sampleDataProvider.getData().firstOrNull { it.id == selectedItem }?.let {
                DetailUI(name = it.name, description = it.description)
            }
            _sampleDataFlow.update { result }
        }
    }
}