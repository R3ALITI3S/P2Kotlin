package com.example.p2kotlinapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p2kotlinapp.network.WeatherApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherScreenViewModel: ViewModel() {

    private val _state = MutableStateFlow(WeatherScreenState())
    val state: StateFlow<WeatherScreenState> = _state.asStateFlow()

    init {
        getCurrentWeatherData()
    }

    private fun getCurrentWeatherData() {
        viewModelScope.launch {
            val listResult = WeatherApi.retrofitService.getCurrentWeather()
            _state.update()
        }
    }
}