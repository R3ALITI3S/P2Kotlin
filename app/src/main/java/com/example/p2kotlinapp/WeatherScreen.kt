package com.example.p2kotlinapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WeatherScreen(state: WeatherScreenState) {

    when(state) {
        WeatherScreenState.Error -> {
            Column {
                Text(text = "Rain: ERROR")
                Text(text = "Clouds: ERROR")
                Text(text = "Is Day: ERROR")
                Text(text = "Full Response: \nERROR")
            }
        }
        WeatherScreenState.Loading -> {
            Column {
                Text(text = "Rain: LOADING...")
                Text(text = "Clouds: LOADING...")
                Text(text = "Is Day: LOADING...")
                Text(text = "Full Response: \nLOADING...")
            }
        }
        is WeatherScreenState.Succes -> {
            Column {
                Text(text = "Rain: ${state.rain}")
                Text(text = "Clouds: ${state.cloudy}")
                Text(text = "Is Day: ${state.isDay}")
                Text(text = "Full Response: \n${state.fullResponse}")
            }
        }
    }
}