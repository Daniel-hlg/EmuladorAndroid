package com.example.miformacionctma.ui.state

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCrearReporte(
    modifier: Modifier = Modifier,
    viewModel: CrearReporteViewModel = viewModel(factory = CrearReporteViewModel.Factory),
    onReporteCreado: () -> Unit = {},
    onNavegarAtras: () -> Unit = {}
) {
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var fotoUri by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear Actividad") },
                navigationIcon = {
                    IconButton(onClick = onNavegarAtras) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        FormularioActividad(
            uiState = FormularioActividadUiState(
                titulo = titulo,
                descripcion = descripcion,
                fecha = fecha,
                fotoUri = fotoUri
            ),
            onTituloChange = { titulo = it },
            onDescripcionChange = { descripcion = it },
            onFechaChange = { fecha = it },
            onFotoUriChange = { fotoUri = it },
            onGuardarClick = {
                if (titulo.isNotBlank()) {
                    viewModel.guardarReporte(
                        titulo = titulo,
                        fecha = fecha,
                        descripcion = descripcion,
                        fotoUri = fotoUri
                    )
                    onReporteCreado()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}