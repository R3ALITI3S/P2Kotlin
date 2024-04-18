package com.example.p2kotlinapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun WeatherScreen(state: State<WeatherScreenState>, navController: NavController) {
    Column {
        Button(onClick = { navController.navigate(Screen.MainScreen.route)}) {
            Text(text = stringResource(id = R.string.to_plant_screen))
        }
        Text(text = "Rain: ${state.value.rain}")
        Text(text = "Clouds: ${state.value.cloudy}")
        Text(text = "Is Day: ${state.value.isDay}")
        Text(text = "Full Response: \n${state.value.fullResponse}")
    }
}