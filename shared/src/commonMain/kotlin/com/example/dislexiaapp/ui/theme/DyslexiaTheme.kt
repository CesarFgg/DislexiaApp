package com.example.dislexiaapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Paleta de colores optimizada para baja fatiga visual y lectura adaptativa (Dislexia)
val CreamBackground = Color(0xFFFAF8F5) // Fondo crema cálido
val SlateDarkText = Color(0xFF1E293B) // Texto pizarra oscuro sin deslumbrar
val BluePrimary = Color(0xFF1E40AF) // Azul claro y de alto contraste
val BlueSecondary = Color(0xFFDBEAFE) // Fondo pastel suave para componentes
val GreenAccent = Color(0xFF15803D) // Verde para confirmación/éxito
val RedAccent = Color(0xFFB91C1C) // Rojo claro para errores
val AmberWarning = Color(0xFFD97706) // Ámbar para advertencias
val CardBorderColor = Color(0xFFCBD5E1)

// Colores para modo de alto contraste
val HighContrastBackground = Color(0xFFFFFFE0) // Fondo crema amarillo suave
val HighContrastText = Color(0xFF000000)

data class DyslexiaPreferences(
    val highContrast: Boolean = false,
    val fontSizeScale: Float = 1.15f,
    val audioEnabled: Boolean = true,
    val visualGuidesEnabled: Boolean = true
)

val LocalDyslexiaPreferences = staticCompositionLocalOf { DyslexiaPreferences() }

private val LightColorScheme = lightColorScheme(
    primary = BluePrimary,
    onPrimary = Color.White,
    primaryContainer = BlueSecondary,
    onPrimaryContainer = BluePrimary,
    background = CreamBackground,
    onBackground = SlateDarkText,
    surface = Color.White,
    onSurface = SlateDarkText,
    error = RedAccent,
    onError = Color.White,
    outline = CardBorderColor
)

private val HighContrastColorScheme = lightColorScheme(
    primary = Color(0xFF000080),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFFF99),
    onPrimaryContainer = Color.Black,
    background = HighContrastBackground,
    onBackground = HighContrastText,
    surface = Color.White,
    onSurface = HighContrastText,
    error = Color(0xFF990000),
    outline = Color.Black
)

@Composable
fun DyslexiaAppTheme(
    preferences: DyslexiaPreferences = DyslexiaPreferences(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (preferences.highContrast) {
        HighContrastColorScheme
    } else {
        LightColorScheme
    }

    CompositionLocalProvider(
        LocalDyslexiaPreferences provides preferences
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography(),
            content = content
        )
    }
}
