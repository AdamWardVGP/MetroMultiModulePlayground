package com.example.data

import com.example.data.models.SampleDataCore
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides


@ContributesTo(AppScope::class)
interface DataModuleGraph {
    @Provides fun getThirdPartyData() : List<SampleDataCore> = listOf(
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