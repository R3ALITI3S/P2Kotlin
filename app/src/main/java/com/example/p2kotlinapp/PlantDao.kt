package com.example.p2kotlinapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Upsert
    suspend fun upsertPlant(plant: Plant)

    @Delete
    suspend fun deletePlant(plant: Plant)

    @Query("SELECT * FROM Plant ORDER BY id ASC")
    fun getPlants(): Flow<List<Plant>>
}