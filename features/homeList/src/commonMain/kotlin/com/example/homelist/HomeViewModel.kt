import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.SampleDataProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

interface HomeViewModel {
    val sampleDataFlow: Flow<List<SampleDataUI>>
}

data class SampleDataUI(val id: Int, val name: String, val description: String)

//@Inject
class HomeViewModelImpl(
    sampleDataProvider: SampleDataProvider,
): ViewModel(), HomeViewModel {

    class Factory(val sampleDataProvider: SampleDataProvider) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModelImpl(sampleDataProvider) as T
        }
    }

    private val _sampleDataFlow: MutableStateFlow<List<SampleDataUI>> = MutableStateFlow(emptyList())
    override val sampleDataFlow: Flow<List<SampleDataUI>>
        get() = _sampleDataFlow

    init {
        viewModelScope.launch {
            _sampleDataFlow.value = sampleDataProvider.getData().map {
                SampleDataUI(it.id, it.name, it.description)
            }
        }
    }
}