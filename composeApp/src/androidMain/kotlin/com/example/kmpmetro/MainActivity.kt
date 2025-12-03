package com.example.kmpmetro

import HomeRoute
import HomeViewModel
import HomeViewModelImpl
import SampleDataUI
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.detail.DetailRoute
import com.example.detail.DetailScreen
import com.example.detail.DetailUI
import com.example.detail.DetailViewModel
import com.example.detail.DetailViewModelImpl
import com.example.homelist.HomeScreen
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import dev.zacsweers.metrox.android.ActivityKey
import dev.zacsweers.metrox.viewmodel.LocalMetroViewModelFactory
import dev.zacsweers.metrox.viewmodel.MetroViewModelFactory
import dev.zacsweers.metrox.viewmodel.assistedMetroViewModel
import dev.zacsweers.metrox.viewmodel.metroViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@ContributesIntoMap(AppScope::class, binding<Activity>())
@ActivityKey(MainActivity::class)
@Inject
class MainActivity(private val metroVmf: MetroViewModelFactory): ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(LocalMetroViewModelFactory provides metroVmf) {

                val navController = rememberNavController()

                Box(modifier = Modifier.fillMaxSize()) {
                    NavHost(navController = navController, startDestination = HomeRoute) {
                        composable<HomeRoute> {
                            HomeScreen(homeViewModel = metroViewModel<HomeViewModelImpl>(),
                                onRowItemClick = { newItem ->
                                    navController.navigate(DetailRoute(newItem.id))
                                }
                            )
                        }
                        composable<DetailRoute> {
                            DetailScreen(
                                detailViewModel = assistedMetroViewModel<DetailViewModelImpl>(),
                                onBackAction = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
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

//@Preview
//@Composable
//fun PreviewHomeScreen() {
//    HomeScreen(
//        homeViewModel = object : HomeViewModel {
//            override val sampleDataFlow: Flow<List<SampleDataUI>>
//                get() = flowOf(listOf(
//                    SampleDataUI(1, "Name 1", "Description 1"),
//                    SampleDataUI(2, "Name 2", "Description 2"),
//                    SampleDataUI(3, "Name 3", "Description 3"),
//                    SampleDataUI(4, "Name 4", "Description 4"),
//                ))
//
//        },
//        onRowItemClick = {}
//    )
//}