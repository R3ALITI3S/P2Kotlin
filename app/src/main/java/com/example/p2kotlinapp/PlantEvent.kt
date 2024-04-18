package com.example.p2kotlinapp

sealed interface PlantEvent {
    object SavePlant: PlantEvent
    data class SetName(val name: String): PlantEvent
    data class SetHealth(val health: Int): PlantEvent
    data class SetGrowth(val growth: Int): PlantEvent
    data class SetWater(val water: Int): PlantEvent
    data class SetSun(val sun: Int): PlantEvent
    data class SetCheck(val lastCheck: Int): PlantEvent

    object ShowDialog: PlantEvent
    object HideDialog: PlantEvent

    data class DeletePlant(val plant: Plant): PlantEvent

    data class UpsertPlant(val plant: Plant): PlantEvent
}