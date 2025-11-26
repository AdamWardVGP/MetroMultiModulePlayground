import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

//@Inject
class SampleDataProvider {
    suspend fun getData(): List<SampleDataCore> {
        return listOf(
            SampleDataCore(1, "Name 1", "Description 1"),
            SampleDataCore(2, "Name 2", "Description 2"),
            SampleDataCore(3, "Name 3", "Description 3"),
            SampleDataCore(4, "Name 4", "Description 4"),
            SampleDataCore(5, "Name 5", "Description 5"),
            SampleDataCore(6, "Name 6", "Description 6"),
            SampleDataCore(7, "Name 7", "Description 7"),
            SampleDataCore(8, "Name 8", "Description 8"),
            SampleDataCore(9, "Name 9", "Description 9"),
            SampleDataCore(10, "Name 10", "Description 10"),
        )
    }
}

data class SampleDataCore(val id: Int, val name: String, val description: String)

