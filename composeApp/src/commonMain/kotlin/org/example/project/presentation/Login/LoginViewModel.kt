package org.example.project.presentation.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.model.User
import org.example.project.domain.use_case.LoginUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.Login -> login(intent.email, intent.password)
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            loginUseCase(email, password)
                .onSuccess {
                    _state.value = LoginState.Success(it)
                }
                .onFailure {
                    _state.value = LoginState.Error(it.message ?: "Unknown error")
                }
        }
    }
}

sealed interface LoginState {
    object Idle : LoginState
    object Loading : LoginState
    data class Success(val user: User) : LoginState
    data class Error(val message: String) : LoginState
}

sealed interface LoginIntent {
    data class Login(val email: String, val password: String) : LoginIntent
}