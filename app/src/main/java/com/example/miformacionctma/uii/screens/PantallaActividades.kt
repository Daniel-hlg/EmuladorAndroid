package com.example.miformacionctma.uii.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.miformacionctma.model.ActividadFormativa
import com.example.miformacionctma.uii.components.TarjetaActividad
import com.example.miformacionctma.ui.state.CrearReporteViewModel

@Composable
fun PantallaActividades(
    modifier: Modifier = Modifier,
    viewModel: CrearReporteViewModel = viewModel(factory = CrearReporteViewModel.Factory),
    onActividadClick: (Int) -> Unit = {},
    onNavegarACrearActividad: () -> Unit = {}
) {
    val actividadesUi by viewModel.listaActividadesState.collectAsState()

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
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column {
                    Text(
                        text = "Mi Formación CTMA",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Actividades formativas",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = "Consulta tus actividades, fechas, estados y progreso.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                    )
                }
            }

            items(actividadesUi) { uiState ->
                // Mapeo dinámico de FormularioActividadUiState a ActividadFormativa
                val actividadModel = ActividadFormativa(
                    id = uiState.id.toIntOrNull() ?: 0,
                    titulo = uiState.titulo,
                    descripcion = uiState.descripcion,
                    fecha = uiState.fecha,
                    estado = uiState.estado,
                    progreso = uiState.progreso.toInt()
                )

                TarjetaActividad(
                    actividad = actividadModel,
                    onCompletar = { onActividadClick(actividadModel.id) },
                    onActividadClick = onActividadClick
                )
            }
        }
    }
}