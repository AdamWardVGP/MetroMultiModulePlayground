@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.homelist

import HomeViewModel
import SampleDataUI
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = CenterVertically
                    ) {
                        Text(
                            text = "List Screen",
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
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
}

@Composable
fun ItemRow(sampleDataUI: SampleDataUI, onItemRowClick: (SampleDataUI) -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        onClick = {
            onItemRowClick(sampleDataUI)
        }
    ) {
        Row {
            Text(text = sampleDataUI.name,
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 18.sp),
                modifier = Modifier.padding(16.dp))
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