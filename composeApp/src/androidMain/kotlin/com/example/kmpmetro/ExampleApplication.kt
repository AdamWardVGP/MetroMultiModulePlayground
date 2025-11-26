package com.example.kmpmetro

import android.app.Application
import android.util.Log
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.createGraph
import kotlinx.coroutines.runBlocking

class ExampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appGraph = createGraph<AppGraph>()
        runBlocking {
            val data = appGraph.sampleDataProvider.getData()
            Log.v("AdamTest", "sample data was $data")
        }
    }
}

@DependencyGraph
interface AppGraph {
    @Provides
    fun getSampleData(): List<SampleDataCore> = sampleData

    val sampleDataProvider: SampleDataProvider
}