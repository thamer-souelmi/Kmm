package org.example.project.presentation.Home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// features/home/HomeViewModel.kt


class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow<HomeState>(HomeState.Default())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.ToggleTheme -> toggleTheme()
        }
    }

    private fun toggleTheme() {
        val current = (_state.value as HomeState.Default).darkTheme
        _state.value = HomeState.Default(darkTheme = !current)
    }
}