package com.example.p2kotlinapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WeatherScreen() {

    Column {
        Text(text = "Rain: ")
        Text(text = "Clouds: ")
        Text(text = "Is Day: ")
    }
}