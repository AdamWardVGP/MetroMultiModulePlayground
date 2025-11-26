package com.example.kmpmetro

import SampleDataUI
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.homelist.DetailScreen
import com.example.homelist.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            var selectedItem by rememberSaveable { mutableStateOf<SampleDataUI?>(null) }
            if(selectedItem == null) {
                HomeScreen(

                ) { newItem ->
                    selectedItem = newItem
                }
            } else {
                DetailScreen() {
                    selectedItem = null
                }
            }

        }
    }
}