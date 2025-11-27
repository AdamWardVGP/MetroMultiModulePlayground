package com.example.data

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo


@ContributesTo(AppScope::class)
interface DataModuleGraph {
    val sampleDataProvider: SampleDataProvider
}