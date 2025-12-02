import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.SampleDataProvider
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import dev.zacsweers.metrox.viewmodel.ViewModelKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

interface HomeViewModel {
    val sampleDataFlow: Flow<List<SampleDataUI>>
}

data class SampleDataUI(val id: Int, val name: String, val description: String)

@Inject
@ViewModelKey(HomeViewModelImpl::class)
@ContributesIntoMap(AppScope::class, binding = binding<ViewModel>())
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