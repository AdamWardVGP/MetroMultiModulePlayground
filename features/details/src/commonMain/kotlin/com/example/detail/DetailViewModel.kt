package com.example.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.toRoute
import com.example.data.SampleDataProvider
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metrox.viewmodel.ViewModelAssistedFactory
import dev.zacsweers.metrox.viewmodel.ViewModelAssistedFactoryKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable class DetailRoute(val id: Int)

interface DetailViewModel {
    val sampleDataFlow: Flow<DetailUI?>
}

data class DetailUI(val name: String, val description: String)

@AssistedInject
class DetailViewModelImpl(
    @Assisted val savedStateHandle: SavedStateHandle,
    sampleDataProvider: SampleDataProvider,
): ViewModel(), DetailViewModel {

    val selectedItem: Int = savedStateHandle.toRoute<DetailRoute>().id
    @AssistedFactory
    @ViewModelAssistedFactoryKey(Factory::class)
    @ContributesIntoMap(AppScope::class)
    interface Factory : ViewModelAssistedFactory {
        override fun create(extras: CreationExtras): ViewModel {
            return create(extras.createSavedStateHandle())
        }

        fun create(@Assisted savedStateHandle: SavedStateHandle): DetailViewModelImpl
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