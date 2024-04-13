package com.example.p2kotlinapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p2kotlinapp.network.WeatherApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class WeatherScreenViewModel: ViewModel() {

    private var _state: WeatherScreenState by mutableStateOf(WeatherScreenState)
    val state = MutableStateFlow(_state)
    init {
        println("Initializing Weather Screen ViewModel")
        getCurrentWeatherData()
    }

    private fun getCurrentWeatherData() {
        println("Launching coroutine..")
        viewModelScope.launch {
            val result = WeatherApi.retrofitService.getCurrentWeather()
            _state.u
        }
    }
}