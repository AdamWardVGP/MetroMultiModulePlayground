package com.example.kmpmetro

import DetailViewModelImpl
import HomeViewModelImpl
import SampleDataUI
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.data.RealSampleDataProvider
import com.example.homelist.DetailScreen
import com.example.homelist.HomeScreen

class MainActivity : ComponentActivity() {

    val sampleDataProvider = RealSampleDataProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            var selectedItem by rememberSaveable { mutableStateOf<SampleDataUI?>(null) }
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