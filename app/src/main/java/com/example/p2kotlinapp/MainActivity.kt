package com.example.p2kotlinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.p2kotlinapp.ui.theme.P2KotlinAppTheme


class MainActivity : ComponentActivity() {

    private val weatherViewModel by viewModels<WeatherScreenViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return WeatherScreenViewModel() as T
                }
            }
        }
    )

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            PlantDatabase::class.java,
            "plants.db"
        ).build()
    }

    private val plantViewModel by viewModels<PlantViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PlantViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P2KotlinAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Left here for future reference
                    //val state = viewModel.state.collectAsState()
                    Navigation(weatherViewModel, plantViewModel)
                    //val state by viewModel.state.collectAsState()
                    //PlantsTestScreen(state = state, onEvent = viewModel::onEvent)
                }
            }
        }
    }
}
