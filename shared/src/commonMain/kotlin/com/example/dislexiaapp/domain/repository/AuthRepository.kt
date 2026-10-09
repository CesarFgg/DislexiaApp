package com.example.dislexiaapp.domain.repository

import com.example.dislexiaapp.data.local.PredefinedUsersJson
import com.example.dislexiaapp.data.model.User
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<User>
    fun getPredefinedUsers(): List<User>
}

class LocalAuthRepository : AuthRepository {

    private val jsonSerializer = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    // Carga de credenciales procesadas dinámicamente desde la fuente JSON
    private val cachedUsers: List<User> by lazy {
        runCatching {
            jsonSerializer.decodeFromString<List<User>>(PredefinedUsersJson.JSON_DATA)
        }.getOrElse { emptyList() }
    }

    override suspend fun login(username: String, password: String): Result<User> {
        // Simulamos latencia de red para emular llamada Ktor
        delay(400)

        val cleanUsername = username.trim()
        val cleanPassword = password.trim()

        if (cleanUsername.isEmpty() || cleanPassword.isEmpty()) {
            return Result.failure(IllegalArgumentException("Por favor, ingresa el usuario y la contraseña."))
        }

        val foundUser = cachedUsers.find { user ->
            user.username.equals(cleanUsername, ignoreCase = true) && user.password == cleanPassword
        }

        return if (foundUser != null) {
            Result.success(foundUser)
        } else {
            Result.failure(IllegalArgumentException("Usuario o contraseña incorrectos. Verifica las credenciales introducidas."))
        }
    }

    override fun getPredefinedUsers(): List<User> {
        return cachedUsers
    }
}
