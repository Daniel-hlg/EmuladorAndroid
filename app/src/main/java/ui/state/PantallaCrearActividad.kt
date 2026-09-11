package com.example.miformacionctma.uii.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.miformacionctma.ui.state.CrearReporteViewModel
import com.example.miformacionctma.ui.state.OperacionUiState

@Composable
fun PantallaCrearReporte(
    modifier: Modifier = Modifier,
    viewModel: CrearReporteViewModel = viewModel(factory = CrearReporteViewModel.Factory),
    onReporteGuardado: () -> Unit = {}
) {
    val context = LocalContext.current
    val operacionState by viewModel.operacionState.collectAsStateWithLifecycle()

    var titulo by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    // Reacción ante los cambios de estado de la operación (Guardar)
    LaunchedEffect(operacionState) {
        when (val estado = operacionState) {
            is OperacionUiState.Exitosa -> {
                Toast.makeText(context, "Reporte guardado con éxito", Toast.LENGTH_SHORT).show()
                viewModel.reiniciarEstadoOperacion()
                onReporteGuardado() // Regresa a la pantalla anterior
            }
            is OperacionUiState.Fallida -> {
                Toast.makeText(context, "Error: ${estado.mensaje}", Toast.LENGTH_LONG).show()
                viewModel.reiniciarEstadoOperacion()
            }
            else -> {}
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Crear Nuevo Reporte",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título de la actividad") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha (ej. 2026-09-11)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción (opcional)") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (titulo.isNotBlank() && fecha.isNotBlank()) {
                        viewModel.guardarReporte(titulo, fecha, descripcion)
                    } else {
                        Toast.makeText(context, "Por favor llena los campos requeridos", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = operacionState !is OperacionUiState.EnCurso,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Reporte")
            }
        }

        // Overlay de carga si la operación está en curso
        if (operacionState is OperacionUiState.EnCurso) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}