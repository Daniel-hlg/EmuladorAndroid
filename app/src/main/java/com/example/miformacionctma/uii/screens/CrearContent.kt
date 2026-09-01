package com.example.miformacionctma.uii.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.viewmodel.CrearUiState

@Composable
fun CrearContent(
    state: CrearUiState,
    onTituloChange: (String) -> Unit,
    onGuardar: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = state.titulo,
            onValueChange = onTituloChange,
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        if (state.errorTitulo != null) {
            Text(
                text = state.errorTitulo,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = onGuardar,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Guardar")
        }
    }
}
