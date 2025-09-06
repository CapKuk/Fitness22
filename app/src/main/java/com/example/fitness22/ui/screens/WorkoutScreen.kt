package com.example.fitness22.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
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
import androidx.compose.ui.text.style.TextAlign
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
import com.example.fitness22.ui.components.WorkoutSummaryRow
import com.example.fitness22.ui.components.RefreshPlanButton

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
    ) {
        // Title with proper spacing
        Text(
            text = "My Workout",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 20.dp)
        )

        // Filter Row - Horizontal Scroll
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                FilterChip(
                    text = uiState.selectedMuscleFilter,
                    onClick = { /* Handle muscle filter */ }
                )
            }
            item {
                FilterChip(
                    text = uiState.selectedDurationFilter,
                    onClick = { /* Handle duration filter */ }
                )
            }
            item {
                FilterChip(
                    text = "Schedule",
                    onClick = { /* Handle schedule filter */ }
                )
            }
            item {
                FilterChip(
                    text = "Basic Exercises",
                    onClick = { /* Handle basic exercises filter */ }
                )
            }
            item {
                FilterChip(
                    text = "Equipment (64)",
                    onClick = { /* Handle equipment filter */ }
                )
            }
            item {
                FilterChip(
                    text = "Goal (1)",
                    onClick = { /* Handle goal filter */ }
                )
            }
            item {
                RefreshPlanButton(
                    onClick = { /* Handle refresh plan */ }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Day Tabs - Dynamic based on WorkoutResponse
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            items(uiState.workoutDays) { workoutDay ->
                DayTab(
                    day = workoutDay.day,
                    isSelected = workoutDay.day == uiState.selectedDay,
                    isCompleted = workoutDay.day == 1, // Mark Day 1 as completed for demo
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
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            // Workout Header
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Week 1/5 - Foundations",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "UPCOMING WORKOUT",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                
                Text(
                    text = "Push",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Workout Summary Stats Row
                WorkoutSummaryRow(exercises = workoutDay.workout)
                
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        items(workoutDay.workout) { exercise ->
            ExerciseItem(
                exercise = exercise
            )
        }

        item {
            // Start Workout Button
            Button(
                onClick = { /* Handle start workout */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "START WORKOUT",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}