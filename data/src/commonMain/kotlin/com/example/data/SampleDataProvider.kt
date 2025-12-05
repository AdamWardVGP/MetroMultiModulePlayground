package com.example.data

import com.example.data.models.SampleDataCore
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

fun interface SampleDataProvider {
    suspend fun getData(): List<SampleDataCore>
}

@Inject
@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class RealSampleDataProvider(val data: List<SampleDataCore>): SampleDataProvider {
    override suspend fun getData(): List<SampleDataCore> {
        return data
    }
}

