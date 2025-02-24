package org.example.project.presentation.Home

// features/home/HomeContract.kt
sealed interface HomeState {
    data class Default(val darkTheme: Boolean = false) : HomeState
}

sealed interface HomeIntent {
    object ToggleTheme : HomeIntent
}