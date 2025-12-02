package com.example.kmpmetro

import android.app.Application
import android.content.Context
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.createGraphFactory
import dev.zacsweers.metrox.android.MetroAppComponentProviders
import dev.zacsweers.metrox.android.MetroApplication
import dev.zacsweers.metrox.viewmodel.ViewModelGraph

class ExampleApplication : Application(), MetroApplication {
    private val appGraph by lazy { createGraphFactory<AppGraph.Factory>().create(this) }

    override val appComponentProviders: MetroAppComponentProviders
        get() = appGraph
}

@DependencyGraph(AppScope::class)
interface AppGraph : MetroAppComponentProviders, ViewModelGraph {

    @Provides
    fun provideApplicationContext(application: Application): Context = application

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(@Provides application: Application): AppGraph
    }
}