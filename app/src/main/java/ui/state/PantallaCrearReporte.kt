package com.example.miformacionctma.ui.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PantallaCrearReporte(
    modifier: Modifier = Modifier,
    viewModel: CrearReporteViewModel = viewModel(factory = CrearReporteViewModel.Factory),
    onReporteCreado: () -> Unit = {}
) {
    var titulo by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título del reporte") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha (ej. 15 de agosto)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (titulo.isNotBlank()) {
                    // Crea y guarda la actividad a través de la interfaz
                    onReporteCreado()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Reporte")
        }
    }
}