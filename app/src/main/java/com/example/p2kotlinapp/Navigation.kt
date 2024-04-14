package com.example.p2kotlinapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(viewModel: WeatherScreenViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainPlantScreen(navController = navController)
        }
        composable(route = Screen.DetailScreen.route) {
            val state = viewModel.state.collectAsState()
            WeatherScreen(state = state, navController = navController)
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navController.navigate(Screen.DetailScreen.route) //withArgs(text) could be used
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "To DetailScreen")
        }
    }
}

@Composable
fun DetailScreen(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Du er på Skærm 2!")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navController.popBackStack()
                      // the back button, takes the top layer and removes it
            },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "To MainScreen")
        }
    }
}


