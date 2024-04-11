package com.example.p2kotlinapp

data class PlantState(
    val plants: List<Plant> = emptyList(),
    val name: String = "",
    val health: Int = 0,
    val growth: Int = 0,
    val water: Int = 0,
    val sun: Int = 0,
    val lastCheck: Int = 0,
    val addingPlant: Boolean = false
)