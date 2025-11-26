@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.homelist

import DetailViewModel
import DetailUI
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel,
    onBackAction: () -> Unit,
) {

    val sampleData by detailViewModel.sampleDataFlow.collectAsStateWithLifecycle(initialValue = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        IconButton(onClick = onBackAction) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = null)
                        }
                        Spacer(modifier = Modifier.weight(1.0f))
                        Text(text = "Detail Screen")
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Card {
                Row {
                    Text(text = sampleData?.name ?: "")
                    Text(text = sampleData?.description ?: "")
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewHomeScreen() {
    DetailScreen(
        detailViewModel = object : DetailViewModel {
            override val sampleDataFlow: Flow<DetailUI>
                get() = flowOf(DetailUI("Name 1", "Description 1"),)
        },
        onBackAction = {}
    )
}
