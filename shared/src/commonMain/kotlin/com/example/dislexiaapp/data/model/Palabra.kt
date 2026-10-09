package com.example.dislexiaapp.data.model

import kotlinx.serialization.Serializable

/**
 * Modelo de datos compartido entre Android e iOS.
 * Representa la definición simplificada de una palabra.
 */
@Serializable
data class Palabra(
    val texto: String,
    val definicion: String,
    val audioDisponible: Boolean = true
)
