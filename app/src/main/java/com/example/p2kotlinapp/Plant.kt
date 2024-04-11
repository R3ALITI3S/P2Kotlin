package com.example.p2kotlinapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plant (
    val name: String,
    val health: Int,
    val growth: Int,
    val water: Int,
    val sun: Int,
    val lastCheck: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
