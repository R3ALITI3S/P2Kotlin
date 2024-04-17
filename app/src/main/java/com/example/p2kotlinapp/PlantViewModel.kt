package com.example.p2kotlinapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlantViewModel(
    private val dao: PlantDao
):ViewModel() {

    private val _plants = dao.getPlants().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(PlantState())

    val state = combine(_state, _plants) {state, plants ->
        state.copy(
            plants = plants
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PlantState())

    fun onEvent(event: PlantEvent) {
        when(event) {
            is PlantEvent.DeletePlant -> {
                viewModelScope.launch {
                    dao.deletePlant(event.plant)
                }
            }
            PlantEvent.HideDialog -> {
                _state.update { it.copy(
                    addingPlant = false
                ) }
            }
            PlantEvent.SavePlant -> {

                val name = state.value.name
                val lastCheck = state.value.lastCheck
                val growth = state.value.growth
                val health = state.value.health
                val water = state.value.water
                val sun = state.value.sun

                if (name.isBlank()) {
                    return
                }

                val plant = Plant(
                    name = name,
                    lastCheck = lastCheck,
                    growth = growth,
                    health = health,
                    water = water,
                    sun = sun
                )

                viewModelScope.launch {
                    dao.upsertPlant(plant)
                }

                _state.update { it.copy(
                    name = "",
                    addingPlant = false
                ) }
            }
            is PlantEvent.SetCheck -> {
                _state.update { it.copy(
                    lastCheck = event.lastCheck
                ) }
            }
            is PlantEvent.SetGrowth -> {
                _state.update { it.copy(
                    growth = event.growth
                ) }
            }
            is PlantEvent.SetHealth -> {
                _state.update { it.copy(
                    health = event.health
                ) }
            }
            is PlantEvent.SetName -> {
                _state.update { it.copy(
                    name = event.name
                ) }
            }
            is PlantEvent.SetSun -> {
                _state.update { it.copy(
                    sun = event.sun
                ) }
            }
            is PlantEvent.SetWater -> {
                _state.update { it.copy(
                    water = event.water
                ) }
            }
            PlantEvent.ShowDialog -> {
                _state.update { it.copy(
                    addingPlant = true
                ) }
            }

            is PlantEvent.UpsertPlant -> {
                viewModelScope.launch {
                    dao.upsertPlant(event.plant)
                }
            }
        }
    }
}