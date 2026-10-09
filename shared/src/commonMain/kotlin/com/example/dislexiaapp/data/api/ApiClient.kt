package com.example.dislexiaapp.data.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import com.example.dislexiaapp.util.Config

/**
 * Cliente HTTP compartido (código común KMP).
 * El motor real (Android/iOS) se resuelve mediante el engine
 * que cada plataforma agrega en su build.gradle.kts.
 */
object ApiClient {
    val instance: HttpClient by lazy {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }

    // La URL base del backend propio vive en una variable de entorno / BuildConfig,
    // nunca hardcodeada directamente en el código de producción.
    val backendBaseUrl: String get() = Config.BACKEND_BASE_URL
}
