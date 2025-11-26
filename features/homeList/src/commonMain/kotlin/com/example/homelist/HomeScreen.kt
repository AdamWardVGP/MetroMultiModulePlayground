package com.example.homelist

import HomeViewModel
import SampleDataUI
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
   onRowItemClick: (SampleDataUI) -> Unit,
) {

    val sampleData by homeViewModel.sampleDataFlow.collectAsStateWithLifecycle(initialValue = emptyList())


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(items = sampleData, key = { it.id }) { item ->
                ItemRow(item, onItemRowClick = onRowItemClick)
            }
        }
    }
}

@Composable
fun ItemRow(sampleDataUI: SampleDataUI, onItemRowClick: (SampleDataUI) -> Unit) {
    Card(onClick = { onItemRowClick(sampleDataUI) }) {
        Row {
            Text(text = sampleDataUI.name)
            Text(text = sampleDataUI.description)
        }
    }
}

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