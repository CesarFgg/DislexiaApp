package com.example.dislexiaapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dislexiaapp.data.model.User
import com.example.dislexiaapp.domain.repository.AuthRepository
import com.example.dislexiaapp.domain.repository.LocalAuthRepository
import com.example.dislexiaapp.ui.theme.DyslexiaPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val usernameInput: String = "",
    val passwordInput: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val authenticatedUser: User? = null,
    val preferences: DyslexiaPreferences = DyslexiaPreferences(),
    val predefinedUsers: List<User> = emptyList(),
    val currentAudioFeedback: String? = null
)

class LoginViewModel(
    private val authRepository: AuthRepository = LocalAuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        // Carga de usuarios predefinidos en JSON para demostración interactiva
        val users = authRepository.getPredefinedUsers()
        _uiState.update { it.copy(predefinedUsers = users) }
    }

    fun onUsernameChange(username: String) {
        _uiState.update {
            it.copy(
                usernameInput = username,
                errorMessage = null
            )
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(
                passwordInput = password,
                errorMessage = null
            )
        }
    }

    fun togglePasswordVisibility() {
        _uiState.update {
            it.copy(isPasswordVisible = !it.isPasswordVisible)
        }
    }

    fun onPreferencesChange(newPreferences: DyslexiaPreferences) {
        _uiState.update {
            it.copy(preferences = newPreferences)
        }
    }

    fun selectPredefinedUser(user: User) {
        _uiState.update {
            it.copy(
                usernameInput = user.username,
                passwordInput = user.password,
                errorMessage = null,
                currentAudioFeedback = "Seleccionaste el perfil de ${user.rol.tituloMostrar}: ${user.nombre}"
            )
        }
    }

    fun playAudioHint(text: String) {
        if (_uiState.value.preferences.audioEnabled) {
            _uiState.update { it.copy(currentAudioFeedback = text) }
        }
    }

    fun clearAudioFeedback() {
        _uiState.update { it.copy(currentAudioFeedback = null) }
    }

    fun login() {
        val currentState = _uiState.value
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = authRepository.login(currentState.usernameInput, currentState.passwordInput)

            result.fold(
                onSuccess = { user ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            authenticatedUser = user,
                            currentAudioFeedback = "¡Bienvenido ${user.nombre}! Redirigiendo a tu panel de ${user.rol.tituloMostrar}."
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message ?: "Error al iniciar sesión",
                            currentAudioFeedback = "Atención: ${error.message}"
                        )
                    }
                }
            )
        }
    }

    fun logout() {
        _uiState.update {
            it.copy(
                authenticatedUser = null,
                usernameInput = "",
                passwordInput = "",
                errorMessage = null,
                currentAudioFeedback = "Has cerrado sesión correctamente."
            )
        }
    }
}
