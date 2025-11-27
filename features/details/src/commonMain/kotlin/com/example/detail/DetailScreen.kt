@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun DetailScreen(
    detailViewModel: DetailViewModel,
    onBackAction: () -> Unit,
) {

    val sampleData by detailViewModel.sampleDataFlow.collectAsStateWithLifecycle(initialValue = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = CenterVertically
                    ) {
                        IconButton(onClick = onBackAction) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = null)
                        }
                        Text(text = "Detail Screen",
                            modifier = Modifier.padding(horizontal = 16.dp))

                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .padding(it)
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Row {
                    Text(text = "Name",
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 18.sp),
                        modifier = Modifier.padding(16.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = sampleData?.name ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp))
                }
                HorizontalDivider()
                Row {
                    Text(text = "Description",
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 18.sp),
                        modifier = Modifier.padding(16.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = sampleData?.description ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp))
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewDetailScreen() {
    DetailScreen(
        detailViewModel = object : DetailViewModel {
            override val sampleDataFlow: Flow<DetailUI?>
                get() = flowOf(DetailUI("Name 1", "Description 1"))
        },
        onBackAction = {}
    )
}
