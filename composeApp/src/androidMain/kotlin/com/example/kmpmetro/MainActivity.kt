package com.example.kmpmetro

import HomeViewModel
import HomeViewModelImpl
import SampleDataUI
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.RealSampleDataProvider
import com.example.detail.DetailScreen
import com.example.detail.DetailUI
import com.example.detail.DetailViewModel
import com.example.detail.DetailViewModelImpl
import com.example.homelist.HomeScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MainActivity : ComponentActivity() {

    val sampleDataProvider = RealSampleDataProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            var selectedItem by remember { mutableStateOf<SampleDataUI?>(null) }
            if(selectedItem == null) {

                HomeScreen(
                    homeViewModel = viewModel(
                        factory = HomeViewModelImpl.Factory(
                            ExampleApplication.appGraph.sampleDataProvider
                        )
                    ),
                    onRowItemClick = { newItem ->
                        selectedItem = newItem
                    }
                )
            } else {
                DetailScreen(
                    detailViewModel = viewModel(
                        key = selectedItem?.id?.toString(),
                        factory = DetailViewModelImpl.Factory(
                            ExampleApplication.appGraph.sampleDataProvider,
                            selectedItem = selectedItem!!.id
                        )
                    ),
                    onBackAction ={
                        selectedItem = null
                    }
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    MaterialTheme {
        DetailScreen(
            detailViewModel = object : DetailViewModel {
                override val sampleDataFlow: Flow<DetailUI>
                    get() = flowOf(DetailUI("Name 1", "Description 1"),)
            },
            onBackAction = {}
        )
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