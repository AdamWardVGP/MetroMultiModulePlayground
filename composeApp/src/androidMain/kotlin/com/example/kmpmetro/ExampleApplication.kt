package com.example.kmpmetro

import android.app.Application
import android.util.Log
import com.example.data.SampleDataProvider
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.createGraph
import kotlinx.coroutines.runBlocking

class ExampleApplication : Application() {

    companion object {
        lateinit var appGraph: AppGraph
    }

    override fun onCreate() {
        super.onCreate()
        appGraph = createGraph<AppGraph>()
        runBlocking {
            appGraph.sampleDataProvider.getData().let {
                Log.v("AdamTest", "gotData $it")
            }
        }
    }
}

@DependencyGraph(scope = AppScope::class)
interface AppGraph {
    //TODO We cam compile without this property? why? seems like the plugin probably generates this
    // behind the scenes, but the IDE doesn't see it.
    val sampleDataProvider: SampleDataProvider
}