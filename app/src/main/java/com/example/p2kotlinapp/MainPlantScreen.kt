package com.example.p2kotlinapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.p2kotlinapp.network.WeatherData
import kotlin.math.abs

const val SUN_DISSIPATION = 10 //Base dissipation of sun
const val MAX_GROWTH = 2 //The amount of growth stages (-1 because of 0 indexation)
const val MAX_HEALTH = 2 //The amount of health stages (-1 because of 0 indexation)
const val MAX_SUN_WATER_DEVIANCY = 20 //How far apart a plant sun and water level is allowed to be apart before loosing health
const val WATERING_AMOUNT = 10 //How much the plants gets watered when the user presses the watering button

val images: Array<IntArray> = arrayOf(
    intArrayOf(R.drawable.g0h0, R.drawable.g0h1, R.drawable.g0h2),
    intArrayOf(R.drawable.g1h0, R.drawable.g1h1, R.drawable.g1h2),
    intArrayOf(R.drawable.g2h0, R.drawable.g2h1, R.drawable.g2h2)
)

@Composable
fun MainPlantScreen(navController: NavController, plantViewModel: PlantViewModel, weatherViewModel: WeatherScreenViewModel, modifier: Modifier = Modifier) {
    val plantState = plantViewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()
    ){
        if (plantState.value.plants.isNotEmpty()) { //Only displays plant info if there is info to display
            Button(
                modifier = Modifier
                    .height(48.dp)
                    .width(280.dp)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 4.dp),
                enabled = true,
                onClick = { WaterPlant(plant = plantState.value.plants[0], plantViewModel) }
            ) {
                Text(text = (stringResource(R.string.waterplant)), modifier = Modifier)
            }
        }
        else {
            plantViewModel.onEvent(PlantEvent.UpsertPlant(Plant(
                growth = 0,
                health = 0,
                water = 0,
                sun = 0,
                name = "Tomato",
                lastCheck = 0,
                id = 0
            )))
        }

        Button(
            modifier = Modifier
                .height(96.dp)
                .width(360.dp)
                .align(Alignment.TopCenter)
                .padding(bottom = 4.dp),
            enabled = true,
            onClick = { navController.navigate(Screen.DetailScreen.route)}
        ) {
            Text(text = (stringResource(R.string.to_weather_screen)), modifier = Modifier)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()


    ) {
        if (plantState.value.plants.isNotEmpty()) {
            Image(
                painter = painterResource(images[plantState.value.plants[0].growth][plantState.value.plants[0].health]),
                contentDescription = "tomatoplant",
                modifier = Modifier
                    .wrapContentSize()
            )
        }


        Spacer(modifier = Modifier.height(16.dp))
        if (plantState.value.plants.isNotEmpty()) { //Only displays plant info if there is info to display
            val oldPlant = plantState.value.plants[0] //Make variable of the old plant for ease of use later

            Text(text = oldPlant.name, fontSize = 18.sp)
            Text(text = "Water: ${oldPlant.water}", fontSize = 18.sp)
            Text(text = "Sun: ${oldPlant.sun}", fontSize = 18.sp)
            Text(text = "Last Visit (UNIX): ${oldPlant.lastCheck}", fontSize = 18.sp)

            UpdatePlantValues(oldPlant = oldPlant, weatherData = weatherViewModel.weatherData, plantViewModel = plantViewModel)
        }
    }
}

fun UpdatePlantValues(oldPlant: Plant, weatherData: WeatherData, plantViewModel: PlantViewModel) {
    //Calculate time since last check
    val elapsedTime = weatherData.time - oldPlant.lastCheck

    //Calculate current sun level. Sun level is the inverse of cloud cover if it is day and 0 if night
    val currentSun = when(weatherData.isDay){
        true -> 100 - weatherData.cloudCover
        else -> 0
    }

    //updates the stored plant with new data if the current time is more than the last stored time
    if (elapsedTime >= 900) {
        //Calculate new health
        var newHealth = oldPlant.health
        if (abs(oldPlant.sun - oldPlant.water) > MAX_SUN_WATER_DEVIANCY && oldPlant.health < MAX_HEALTH) {
            newHealth += 1
        }else if (oldPlant.health > 0) {
            newHealth -= 1
        }

        //Calculate new growth
        var newGrowth = oldPlant.growth
        if (newHealth == 0 && newGrowth < MAX_GROWTH) {
            newGrowth += 1
        }

        //Calculate the new value for the amount of sun the plant has sun
        var newSun = oldPlant.sun - SUN_DISSIPATION + currentSun
        //Restricts sun to always be 0 or above
        if (newSun < 0) {
            newSun = 0
        }

        //Calculate plant water level
        var newWater = oldPlant.water - currentSun
        //Restricts water to always be 0 or above
        if (newWater < 0) {
            newWater = 0
        }


        val newPlant = Plant(
            id = oldPlant.id,
            name = oldPlant.name,
            growth = newGrowth,
            health = newHealth,
            sun = newSun,
            water = newWater,
            lastCheck = weatherData.time
        )

        plantViewModel.onEvent(PlantEvent.UpsertPlant(newPlant))
    }
}

fun WaterPlant(plant: Plant, plantViewModel: PlantViewModel) {
    val newPlant = Plant(
        id = plant.id,
        name = plant.name,
        growth = plant.growth,
        health = plant.health,
        sun = plant.sun,
        water = plant.water + WATERING_AMOUNT,
        lastCheck = plant.lastCheck
    )

    plantViewModel.onEvent(PlantEvent.UpsertPlant(newPlant))
}