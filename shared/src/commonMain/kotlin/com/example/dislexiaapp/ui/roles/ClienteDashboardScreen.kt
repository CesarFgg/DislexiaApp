package com.example.dislexiaapp.ui.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dislexiaapp.data.model.User

@Composable
fun ClienteDashboardScreen(
    user: User,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedWordIndex by remember { mutableStateOf(0) }
    var fontScale by remember { mutableStateOf(18.sp) }
    var audioStatusMessage by remember { mutableStateOf("Toca 'Escuchar' para oír la palabra claramente.") }

    val palabrasPractica = listOf(
        Pair("SOL", "S - O - L (Objeto brillante en el cielo)"),
        Pair("CASA", "C - A - S - A (Lugar donde vivimos)"),
        Pair("AMIGO", "A - M - I - G - O (Persona querida)"),
        Pair("LECTURA", "L - E - C - T - U - R - A (Aprender leyendo)")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Tarjeta de Bienvenida adaptable
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(user.avatarEmoji, fontSize = 32.sp)
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "¡Hola, ${user.nombre}!",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Perfil: ${user.rol.tituloMostrar}",
                            fontSize = 13.sp
                        )
                    }
                }
                Text("✏️ Zona de Práctica Didáctica para Dislexia", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
        }

        // Ajustador de tamaño de letra adaptado a pantallas móviles
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text("🔤 Tamaños de Letra:", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    FilterChip(
                        selected = fontScale == 16.sp,
                        onClick = { fontScale = 16.sp },
                        label = { Text("Normal", fontSize = 11.sp) },
                        modifier = Modifier.weight(1f)
                    )
                    FilterChip(
                        selected = fontScale == 22.sp,
                        onClick = { fontScale = 22.sp },
                        label = { Text("Grande", fontSize = 11.sp) },
                        modifier = Modifier.weight(1f)
                    )
                    FilterChip(
                        selected = fontScale == 28.sp,
                        onClick = { fontScale = 28.sp },
                        label = { Text("Extra", fontSize = 11.sp) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Ejercicio Didáctico Interactivo de Lectura de Palabras
        Text("📖 Ejercicio de Lectura Guiada", fontSize = 16.sp, fontWeight = FontWeight.Bold)

        val currentWord = palabrasPractica[selectedWordIndex]

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Palabra ${selectedWordIndex + 1} de ${palabrasPractica.size}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = currentWord.first,
                        fontSize = fontScale,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                        letterSpacing = 2.sp
                    )
                }

                Text(
                    text = currentWord.second,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )

                // Botón de apoyo auditivo
                ElevatedButton(
                    onClick = {
                        audioStatusMessage = "🔊 Reproduciendo: '${currentWord.first}'"
                    },
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Text("🎧 Escuchar Pronunciación", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }

                Text(
                    text = audioStatusMessage,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            if (selectedWordIndex > 0) selectedWordIndex--
                        },
                        enabled = selectedWordIndex > 0,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("⬅️ Anterior", fontSize = 12.sp)
                    }

                    Button(
                        onClick = {
                            if (selectedWordIndex < palabrasPractica.size - 1) selectedWordIndex++
                        },
                        enabled = selectedWordIndex < palabrasPractica.size - 1,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Siguiente ➡️", fontSize = 12.sp)
                    }
                }
            }
        }

        OutlinedButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth().height(48.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("🚪 Cerrar Sesión", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}
