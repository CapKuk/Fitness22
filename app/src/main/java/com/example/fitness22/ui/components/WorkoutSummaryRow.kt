package com.example.fitness22.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness22.data.models.Exercise

@Composable
fun WorkoutSummaryRow(
    exercises: List<Exercise>,
    modifier: Modifier = Modifier
) {
    // Calculate workout summary
    val exerciseCount = exercises.size
    val estimatedTime = exerciseCount * 8 + 5
    val estimatedCalories = exerciseCount * 45

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Exercise Count
        SummaryItem(
            icon = "💪",
            count = "$exerciseCount",
            label = "Exercises"
        )
        
        // Time
        SummaryItem(
            icon = "⏱️",
            count = "$estimatedTime",
            label = "Min"
        )
        
        // Calories
        SummaryItem(
            icon = "🔥",
            count = "$estimatedCalories",
            label = "Cal"
        )
    }
}

@Composable
private fun SummaryItem(
    icon: String,
    count: String,
    label: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = icon,
            fontSize = 18.sp
        )
        Text(
            text = count,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}