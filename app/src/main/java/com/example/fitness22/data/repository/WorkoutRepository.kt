package com.example.fitness22.data.repository

import android.content.Context
import com.example.fitness22.data.models.WorkoutResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class WorkoutRepository(private val context: Context) {
    
    private val gson = Gson()
    
    suspend fun loadWorkouts(): Result<WorkoutResponse> = withContext(Dispatchers.IO) {
        try {
            val jsonString = context.assets.open("workouts.json").bufferedReader().use { it.readText() }
            val workoutResponse = gson.fromJson(jsonString, WorkoutResponse::class.java)
            Result.success(workoutResponse)
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}