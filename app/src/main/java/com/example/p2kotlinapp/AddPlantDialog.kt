package com.example.p2kotlinapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlantDialog(
    state: PlantState,
    onEvent: (PlantEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onEvent(PlantEvent.HideDialog)}
    ) {
        Column(Modifier.background(color =  Color.White)) {
            Text(text = "Add plant")
            TextField(value = state.name,
                onValueChange = { onEvent(PlantEvent.SetName(it)) },
                placeholder = { Text(text = "Enter plant name...")}
                )

            Button(onClick = { onEvent(PlantEvent.SavePlant) }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Add")
            }
        }
    }
}