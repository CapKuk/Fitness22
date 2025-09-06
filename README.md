# Fitness22 - Android Workout Management App

A modern Android fitness application built with Kotlin and Jetpack Compose, designed to help users manage their workout routines with an intuitive and engaging interface.

## Features

- **Workout Management**: Browse and customize workout routines across multiple days
- **Exercise Library**: View detailed exercise information with muscle group targeting
- **Progress Tracking**: Monitor your fitness journey over time
- **Customizable Workouts**: Edit workout names and personalize your routine
- **Modern UI**: Material 3 design with smooth animations and responsive layout

## Screenshots

The app features a clean, modern interface with:
- Bottom navigation across four main sections
- Interactive workout cards with exercise details
- Customizable workout naming with inline editing
- Day-based workout organization

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material 3
- **Architecture**: MVVM with Repository pattern
- **State Management**: StateFlow and Compose state
- **Navigation**: Jetpack Navigation Compose
- **Data Storage**: JSON assets with Gson parsing
- **Image Loading**: Coil for Compose
- **Target SDK**: Android 36 (minimum SDK 33)

## Architecture

The app follows modern Android development practices:

```
├── ui/
│   ├── screens/          # Main app screens (WorkoutScreen, ExercisesScreen, etc.)
│   ├── components/       # Reusable UI components
│   └── theme/           # Material 3 theme configuration
├── data/
│   ├── models/          # Data classes (Exercise, WorkoutDay, WorkoutResponse)
│   └── repository/      # Data layer with Repository pattern
└── navigation/          # Compose navigation setup
```

## Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- Android SDK 36
- Kotlin 1.9+
- Gradle 8.0+

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd Fitness22
```

2. Open the project in Android Studio

3. Sync the project with Gradle files

4. Run the app on an emulator or physical device

### Build Commands

```bash
# Build the entire project
./gradlew build

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Install debug build on connected device
./gradlew installDebug

# Clean build artifacts
./gradlew clean
```

### Testing

```bash
# Run all unit tests
./gradlew test

# Run debug unit tests
./gradlew testDebugUnitTest

# Run instrumentation tests on connected device
./gradlew connectedAndroidTest
```

### Code Quality

```bash
# Run lint checks
./gradlew lint

# Run lint on debug variant
./gradlew lintDebug

# Apply safe lint suggestions
./gradlew lintFix
```

## Project Structure

### Data Management

The app uses a JSON-based data structure stored in `app/src/main/assets/workouts.json`:

```json
{
  "workouts": [
    {
      "day": 1,
      "workout": [
        {
          "exercise_id": 1,
          "exercise_name": "Single Leg Extension",
          "muscle_group": "Legs",
          "amount_of_sets": 3,
          "rep_range": "10-12",
          "weight_amount": "8.0"
        }
      ]
    }
  ]
}
```

### Key Components

- **WorkoutScreen**: Main workout interface with editable workout names
- **ExerciseItem**: Reusable component displaying exercise details
- **DayTab**: Interactive day selector with completion states
- **FilterChip**: Customizable filter components
- **WorkoutRepository**: Handles data loading and parsing

## Development

### State Management

The app uses modern Android state management patterns:

- **StateFlow** in ViewModels for reactive UI updates
- **Compose state** for local component state
- **UI state classes** containing all screen-specific data
- **Callback functions** for event handling

### UI Patterns

- **Material 3 Design System** with dynamic theming
- **Bottom navigation** with proper state preservation
- **Focus management** for text editing components
- **Responsive layouts** adapting to different screen sizes

### Adding New Features

1. **New Screen**: Create composable in `ui/screens/` with corresponding ViewModel
2. **Reusable Component**: Add to `ui/components/` following existing patterns
3. **Data Model**: Define in `data/models/` and update repository as needed
4. **Navigation**: Update `MainNavigation.kt` with new routes

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Architecture Notes

This project is currently in transition from a Fragment-based architecture to full Jetpack Compose. Some legacy Fragment components remain alongside new Compose screens, providing a learning opportunity for hybrid Android app development.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Built with Android's modern toolkit: Jetpack Compose, Material 3, and Architecture Components
- Exercise data and imagery sourced from fitness industry standards
- UI/UX inspired by modern fitness and wellness applications