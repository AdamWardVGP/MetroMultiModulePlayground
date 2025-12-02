package com.example.homelist

import HomeViewModel
import SampleDataUI
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        homeViewModel = object : HomeViewModel {
            override val sampleDataFlow: Flow<List<SampleDataUI>>
                get() = flowOf(listOf(
                    SampleDataUI(1, "Name 1", "Description 1"),
                    SampleDataUI(2, "Name 2", "Description 2"),
                    SampleDataUI(3, "Name 3", "Description 3"),
                    SampleDataUI(4, "Name 4", "Description 4"),
                ))

        },
        onRowItemClick = {}
    )
}