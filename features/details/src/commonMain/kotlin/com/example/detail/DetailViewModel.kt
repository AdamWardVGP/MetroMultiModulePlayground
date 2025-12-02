package com.example.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.SampleDataProvider
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.binding
import dev.zacsweers.metrox.viewmodel.ManualViewModelAssistedFactory
import dev.zacsweers.metrox.viewmodel.ManualViewModelAssistedFactoryKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface DetailViewModel {
    val sampleDataFlow: Flow<DetailUI?>
}

data class DetailUI(val name: String, val description: String)

@AssistedInject
class DetailViewModelImpl(
    sampleDataProvider: SampleDataProvider,
    @Assisted val selectedItem: Int
): ViewModel(), DetailViewModel {

    @AssistedFactory
    @ManualViewModelAssistedFactoryKey(Factory::class)
    @ContributesIntoMap(AppScope::class, binding = binding<ManualViewModelAssistedFactory>())
    interface Factory : ManualViewModelAssistedFactory {
        fun create(@Assisted selectedItem: Int): DetailViewModelImpl
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