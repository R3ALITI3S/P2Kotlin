package com.example.p2kotlinapp

sealed interface WeatherScreenState {
    data class Succes(val rain: String = "",
                      val cloudy: String = "",
                      val isDay: String = "",
                      val fullResponse: String = ""): WeatherScreenState
    object Loading: WeatherScreenState
    object Error:WeatherScreenState
}