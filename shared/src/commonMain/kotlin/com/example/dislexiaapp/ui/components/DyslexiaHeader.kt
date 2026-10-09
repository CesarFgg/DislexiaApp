package com.example.dislexiaapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dislexiaapp.ui.theme.DyslexiaPreferences

/**
 * Barra superior con controles interactivos de accesibilidad didáctica para dislexia.
 * Adaptada para pantallas móviles de cualquier ancho.
 */
@Composable
fun DyslexiaAccessibilityToolbar(
    preferences: DyslexiaPreferences,
    onPreferencesChange: (DyslexiaPreferences) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "♿ Accesibilidad:",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(end = 4.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Alternar Alto Contraste
                FilterChip(
                    selected = preferences.highContrast,
                    onClick = {
                        onPreferencesChange(preferences.copy(highContrast = !preferences.highContrast))
                    },
                    label = {
                        Text(
                            text = if (preferences.highContrast) "🟡 Contraste" else "⚪ Suave",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                )

                // Alternar Apoyo Sonoro
                IconButton(
                    onClick = {
                        onPreferencesChange(preferences.copy(audioEnabled = !preferences.audioEnabled))
                    },
                    modifier = Modifier.size(38.dp)
                ) {
                    Text(
                        text = if (preferences.audioEnabled) "🔊" else "🔇",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}
