package com.example.dislexiaapp

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dislexiaapp.data.model.UserRole
import com.example.dislexiaapp.ui.auth.LoginScreen
import com.example.dislexiaapp.ui.auth.LoginViewModel
import com.example.dislexiaapp.ui.roles.*
import com.example.dislexiaapp.ui.theme.DyslexiaAppTheme

/**
 * Componente principal de la aplicación en Compose Multiplatform.
 * Administra el estado de autenticación, el tema adaptativo para dislexia
 * y la navegación enrutada según el Rol del usuario.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    viewModel: LoginViewModel = viewModel { LoginViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()

    DyslexiaAppTheme(preferences = uiState.preferences) {
        val user = uiState.authenticatedUser

        Scaffold(
            topBar = {
                if (user != null) {
                    TopAppBar(
                        title = {
                            Text(
                                text = "${user.rol.icono} ${user.rol.tituloMostrar}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        actions = {
                            TextButton(onClick = viewModel::logout) {
                                Text(
                                    text = "🚪 Salir",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Crossfade(targetState = user) { currentUser ->
                    if (currentUser == null) {
                        LoginScreen(
                            state = uiState,
                            onUsernameChange = viewModel::onUsernameChange,
                            onPasswordChange = viewModel::onPasswordChange,
                            onTogglePasswordVisibility = viewModel::togglePasswordVisibility,
                            onPreferencesChange = viewModel::onPreferencesChange,
                            onSelectPredefinedUser = viewModel::selectPredefinedUser,
                            onPlayAudioHint = viewModel::playAudioHint,
                            onClearAudioFeedback = viewModel::clearAudioFeedback,
                            onLoginClick = viewModel::login
                        )
                    } else {
                        // Enrutamiento dinámico según el Rol asignado en las credenciales JSON
                        when (currentUser.rol) {
                            UserRole.ADMINISTRADOR -> AdminDashboardScreen(
                                user = currentUser,
                                onLogout = viewModel::logout
                            )
                            UserRole.SUPERVISOR -> SupervisorDashboardScreen(
                                user = currentUser,
                                onLogout = viewModel::logout
                            )
                            UserRole.CLIENTE -> ClienteDashboardScreen(
                                user = currentUser,
                                onLogout = viewModel::logout
                            )
                            UserRole.DOCTOR -> DoctorDashboardScreen(
                                user = currentUser,
                                onLogout = viewModel::logout
                            )
                            UserRole.VENDEDOR -> VendedorDashboardScreen(
                                user = currentUser,
                                onLogout = viewModel::logout
                            )
                        }
                    }
                }
            }
        }
    }
}
