package com.example.fitness22.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitness22.data.models.WorkoutDay
import com.example.fitness22.data.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WorkoutViewModel(
    private val repository: WorkoutRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WorkoutUiState())
    val uiState: StateFlow<WorkoutUiState> = _uiState.asStateFlow()

    init {
        loadWorkouts()
    }

    private fun loadWorkouts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            repository.loadWorkouts()
                .onSuccess { workoutResponse ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        workoutDays = workoutResponse.workouts,
                        selectedDay = if (workoutResponse.workouts.isNotEmpty()) workoutResponse.workouts[0].day else 1
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }

    fun selectDay(day: Int) {
        _uiState.value = _uiState.value.copy(selectedDay = day)
    }

    fun selectMuscleFilter(muscle: String) {
        _uiState.value = _uiState.value.copy(selectedMuscleFilter = muscle)
    }

    fun selectDurationFilter(duration: String) {
        _uiState.value = _uiState.value.copy(selectedDurationFilter = duration)
    }

    fun toggleEditMode() {
        _uiState.value = _uiState.value.copy(isEditMode = !_uiState.value.isEditMode)
    }

    fun updateWorkoutName(name: String) {
        _uiState.value = _uiState.value.copy(workoutName = name)
    }
}

data class WorkoutUiState(
    val isLoading: Boolean = false,
    val workoutDays: List<WorkoutDay> = emptyList(),
    val selectedDay: Int = 1,
    val selectedMuscleFilter: String = "Muscles (16)",
    val selectedDurationFilter: String = "45-60 Min",
    val error: String? = null,
    val isEditMode: Boolean = false,
    val workoutName: String = "UPCOMING WORKOUT"
) {
    val currentWorkout: WorkoutDay?
        get() = workoutDays.find { it.day == selectedDay }
}

class WorkoutViewModelFactory(
    private val repository: WorkoutRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}