package com.example.dislexiaapp.ui.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dislexiaapp.data.model.User

@Composable
fun SupervisorDashboardScreen(
    user: User,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
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
                            text = "Vista: ${user.rol.tituloMostrar}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Supervisora: ${user.nombre}",
                            fontSize = 14.sp
                        )
                    }
                }
                Text("📊 Evaluación y supervisión del desempeño pedagógico en dislexia.", fontSize = 12.sp)
            }
        }

        Text("📋 Reporte de Alumnos Asignados", fontSize = 16.sp, fontWeight = FontWeight.Bold)

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("🎒 Estudiante: Mateo Hernández", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                LinearProgressIndicator(progress = { 0.78f }, modifier = Modifier.fillMaxWidth().height(8.dp))
                Text("Progreso semanal: 78% completado", fontSize = 12.sp)
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("🎒 Estudiante: Sofía Gómez", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                LinearProgressIndicator(progress = { 0.92f }, modifier = Modifier.fillMaxWidth().height(8.dp))
                Text("Progreso semanal: 92% completado", fontSize = 12.sp)
            }
        }

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 48.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("📈 Generar Informe General de Progreso", fontSize = 14.sp)
        }

        OutlinedButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 48.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("🚪 Cerrar Sesión", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}
