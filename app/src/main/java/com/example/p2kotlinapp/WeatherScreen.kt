package com.example.p2kotlinapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WeatherScreen(state: WeatherScreenState) {
    Column {
        Text(text = "Rain: ${state.rain}")
        Text(text = "Clouds: ${state.cloudy}")
        Text(text = "Is Day: ${state.isDay}")
        Text(text = "Full Response: \n${state.fullResponse}")
    }
}