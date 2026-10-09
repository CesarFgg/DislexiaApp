package com.example.dislexiaapp.data.model

import kotlinx.serialization.Serializable

/**
 * Roles o perfiles de trabajo soportados en la aplicación.
 */
@Serializable
enum class UserRole(
    val tituloMostrar: String,
    val descripcion: String,
    val icono: String
) {
    ADMINISTRADOR(
        tituloMostrar = "Administrador del Sistema",
        descripcion = "Gestión global de usuarios, configuraciones y logs del sistema.",
        icono = "⚙️"
    ),
    SUPERVISOR(
        tituloMostrar = "Supervisor Académico",
        descripcion = "Monitoreo de progreso, informes de rendimiento y tareas grupales.",
        icono = "📊"
    ),
    CLIENTE(
        tituloMostrar = "Cliente / Estudiante",
        descripcion = "Acceso a ejercicios interactivos de lectura y apoyo para dislexia.",
        icono = "✏️"
    ),
    DOCTOR(
        tituloMostrar = "Doctor / Terapeuta",
        descripcion = "Evaluación clínica, diagnóstico adaptativo y seguimiento de terapias.",
        icono = "🩺"
    ),
    VENDEDOR(
        tituloMostrar = "Vendedor / Licencias",
        descripcion = "Gestión de paquetes de suscripción, licencias y clientes comerciales.",
        icono = "💼"
    )
}

/**
 * Modelo de datos de Usuario para autenticación y perfil.
 */
@Serializable
data class User(
    val id: String,
    val username: String,
    val password: String,
    val nombre: String,
    val email: String,
    val rol: UserRole,
    val avatarEmoji: String = "👤"
)

/**
 * Solicitud de inicio de sesión.
 */
@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)

/**
 * Respuesta del servicio de autenticación.
 */
@Serializable
data class AuthResponse(
    val success: Boolean,
    val message: String,
    val user: User? = null
)
