import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.SampleDataProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface DetailViewModel {
    val sampleDataFlow: Flow<SampleDataUI?>
}

data class SampleDataUI(val id: Int, val name: String, val description: String)

//@Inject
class DetailViewModelImpl(
    sampleDataProvider: SampleDataProvider,
    val selectedItem: Int
): ViewModel(), DetailViewModel {

    private val _sampleDataFlow: MutableStateFlow<SampleDataUI?> = MutableStateFlow(null)
    override val sampleDataFlow: Flow<SampleDataUI?>
        get() = _sampleDataFlow

    init {
        viewModelScope.launch {
            val result = sampleDataProvider.getData().firstOrNull { it.id == selectedItem }?.let {
                SampleDataUI(it.id, it.name, it.description)
            }
            _sampleDataFlow.update { result }
        }
    }
}