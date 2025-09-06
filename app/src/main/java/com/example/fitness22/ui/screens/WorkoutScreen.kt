package com.example.fitness22.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.fitness22.data.models.Exercise
import com.example.fitness22.data.models.WorkoutDay
import com.example.fitness22.data.repository.WorkoutRepository
import com.example.fitness22.ui.components.FilterChip
import com.example.fitness22.ui.components.DayTab
import com.example.fitness22.ui.components.ExerciseItem
import com.example.fitness22.ui.components.WorkoutSummaryCard

@Composable
fun WorkoutScreen(
    viewModel: WorkoutViewModel = viewModel(
        factory = WorkoutViewModelFactory(WorkoutRepository(LocalContext.current))
    )
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "My Workout",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Filter Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                text = uiState.selectedMuscleFilter,
                onClick = { /* Handle muscle filter */ }
            )
            FilterChip(
                text = uiState.selectedDurationFilter,
                onClick = { /* Handle duration filter */ }
            )
            FilterChip(
                text = "Schedule",
                onClick = { /* Handle schedule filter */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Day Tabs - Dynamic based on WorkoutResponse
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            items(uiState.workoutDays) { workoutDay ->
                DayTab(
                    day = workoutDay.day,
                    isSelected = workoutDay.day == uiState.selectedDay,
                    onClick = { viewModel.selectDay(workoutDay.day) }
                )
            }
        }

        // Workout Content
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            uiState.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Error: ${uiState.error}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            uiState.currentWorkout != null -> {
                WorkoutContent(
                    workoutDay = uiState.currentWorkout!!
                )
            }
        }
    }
}

@Composable
private fun WorkoutContent(workoutDay: WorkoutDay) {
    LazyColumn {
        item {
            // Week info
            Text(
                text = "Week 1/5 - Foundations",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            // Workout Summary Card
            WorkoutSummaryCard(
                exercises = workoutDay.workout,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        items(workoutDay.workout) { exercise ->
            ExerciseItem(
                exercise = exercise,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            // Start Workout Button
            Button(
                onClick = { /* Handle start workout */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFD700),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "START WORKOUT",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}