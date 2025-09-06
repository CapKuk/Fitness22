package com.example.fitness22.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object MyWorkout : BottomNavItem(
        route = "my_workout",
        icon = Icons.Default.Home,
        title = "My Workout"
    )
    
    object Exercises : BottomNavItem(
        route = "exercises", 
        icon = Icons.Default.Add,
        title = "Exercises"
    )
    
    object Progress : BottomNavItem(
        route = "progress",
        icon = Icons.Default.Check,
        title = "Progress"
    )
    
    object Settings : BottomNavItem(
        route = "settings",
        icon = Icons.Default.Person,
        title = "Settings"
    )
}