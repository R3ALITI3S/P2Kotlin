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

    private var _state: WeatherScreenState by mutableStateOf(WeatherScreenState.Loading)
    val state = MutableStateFlow(_state)
    init {
        getCurrentWeatherData()
    }

    private fun getCurrentWeatherData() {
        viewModelScope.launch {
            _state = try {
                val result = WeatherApi.retrofitService.getCurrentWeather()
                WeatherScreenState.Succes(result)
            }
            catch (e :IOException) {
                WeatherScreenState.Error
            }
        }
    }
}