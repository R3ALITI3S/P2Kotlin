package com.example.p2kotlinapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlantsTestScreen(
    state: PlantState,
    onEvent: (PlantEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(PlantEvent.ShowDialog)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add plant")
            }
        }
    ) {padding ->

        if (state.addingPlant) {
            AddPlantDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.plants) {plant ->
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = plant.name, fontSize = 20.sp)
                        Text(text = "health: ${plant.health}, growth: ${plant.growth}", fontSize = 12.sp)
                    }

                    IconButton(onClick = {
                        onEvent(PlantEvent.DeletePlant(plant))
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Plant")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TomatoesScreenPreview() {


}
