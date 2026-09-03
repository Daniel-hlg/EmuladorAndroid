package com.example.miformacionctma.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.ui.state.FormularioActividadUiState

@Composable
fun FormularioActividad(
    uiState: FormularioActividadUiState,
    onTituloChange: (String) -> Unit,
    onDescripcionChange: (String) -> Unit,
    onFechaChange: (String) -> Unit,
    onPrioridadChange: (Int) -> Unit,
    onProgresoChange: (Int) -> Unit,
    onGuardar: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        // =========================
        // TÍTULO
        // =========================

        OutlinedTextField(
            value = uiState.titulo,
            onValueChange = onTituloChange,
            label = {
                Text("Título")
            },
            isError = uiState.errores.containsKey("titulo"),
            supportingText = {
                uiState.errores["titulo"]?.let {
                    Text(it)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        // =========================
        // DESCRIPCIÓN
        // =========================

        OutlinedTextField(
            value = uiState.descripcion,
            onValueChange = onDescripcionChange,
            label = {
                Text("Descripción")
            },
            isError = uiState.errores.containsKey("descripcion"),
            supportingText = {
                uiState.errores["descripcion"]?.let {
                    Text(it)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        // =========================
        // FECHA
        // =========================

        OutlinedTextField(
            value = uiState.fecha,
            onValueChange = onFechaChange,
            label = {
                Text("Fecha")
            },
            placeholder = {
                Text("Ejemplo: 20 de agosto")
            },
            isError = uiState.errores.containsKey("fecha"),
            supportingText = {
                uiState.errores["fecha"]?.let {
                    Text(it)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // =========================
        // PRIORIDAD
        // =========================

        Text(
            text = "Prioridad: ${textoPrioridad(uiState.prioridad)}"
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Slider(
            value = uiState.prioridad.toFloat(),
            onValueChange = {
                onPrioridadChange(it.toInt())
            },
            valueRange = 1f..3f,
            steps = 1,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        // =========================
        // PROGRESO
        // =========================

        Text(
            text = "Progreso: ${uiState.progreso}%"
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Slider(
            value = uiState.progreso.toFloat(),
            onValueChange = {
                onProgresoChange(it.toInt())
            },
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth()
        )

        if (uiState.errores.containsKey("progreso")) {
            Text(
                text = uiState.errores["progreso"] ?: ""
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // =========================
        // GUARDAR
        // =========================

        Button(
            onClick = onGuardar,
            enabled = uiState.puedeGuardar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar actividad")
        }
    }
}


// =====================================
// TEXTO DE PRIORIDAD
// =====================================

private fun textoPrioridad(
    prioridad: Int
): String {

    return when (prioridad) {

        1 -> "Baja"

        2 -> "Media"

        3 -> "Alta"

        else -> "Baja"
    }
}