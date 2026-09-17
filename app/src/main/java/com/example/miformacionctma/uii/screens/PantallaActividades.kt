package com.example.miformacionctma.uii.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.miformacionctma.model.ActividadFormativa
import com.example.miformacionctma.ui.state.CrearReporteViewModel
import com.example.miformacionctma.ui.state.ListadoUiState
import com.example.miformacionctma.uii.components.TarjetaActividad

@Composable
fun PantallaActividades(
    modifier: Modifier = Modifier,
    viewModel: CrearReporteViewModel = viewModel(factory = CrearReporteViewModel.Factory),
    onActividadClick: (Int) -> Unit = {},
    onNavegarACrearActividad: () -> Unit = {}
) {
    val listadoState by viewModel.listadoUiState.collectAsStateWithLifecycle()
    val textoBusqueda by viewModel.textoBusqueda.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavegarACrearActividad,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Crear nueva actividad"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Mi Formación CTMA",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Actividades formativas",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 8.dp)
            )

            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { viewModel.actualizarBusqueda(it) },
                label = { Text("Buscar actividad...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                singleLine = true
            )

            when (val estado = listadoState) {
                is ListadoUiState.Cargando -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is ListadoUiState.Vacio -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No se encontraron actividades registradas.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                is ListadoUiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Ocurrió un error: ${estado.mensaje}",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                is ListadoUiState.Contenido -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(estado.lista) { uiState ->
                            val actividadModel = ActividadFormativa(
                                id = uiState.id.toIntOrNull() ?: 0,
                                titulo = uiState.titulo,
                                descripcion = uiState.descripcion,
                                fecha = uiState.fecha,
                                estado = uiState.estado,
                                progreso = uiState.progreso.toInt(),
                                fotoUri = uiState.fotoUri // <- Mapeo de evidencia fotográfica agregada
                            )

                            TarjetaActividad(
                                actividad = actividadModel,
                                onCompletar = {
                                    viewModel.completarActividad(actividadModel.id)
                                },
                                onActividadClick = onActividadClick
                            )
                        }
                    }
                }
            }
        }
    }
}