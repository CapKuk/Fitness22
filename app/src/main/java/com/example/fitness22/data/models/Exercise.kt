package com.example.fitness22.data.models

import com.google.gson.annotations.SerializedName

data class Exercise(
    @SerializedName("exercise_id")
    val id: Int,
    
    @SerializedName("exercise_name")
    val name: String,
    
    @SerializedName("exercise_thumbnail")
    val thumbnail: String,
    
    @SerializedName("muscle_group")
    val muscleGroup: String,
    
    @SerializedName("muscle_group_image")
    val muscleGroupImage: String,
    
    @SerializedName("amount_of_sets")
    val sets: Int,
    
    @SerializedName("rep_range")
    val reps: String,
    
    @SerializedName("weight_amount")
    val weight: String?
)