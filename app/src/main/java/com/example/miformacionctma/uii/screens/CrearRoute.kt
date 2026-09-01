package com.example.miformacionctma.uii.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.miformacionctma.viewmodel.CrearReporteViewModel

@Composable
fun CrearRoute(
    viewModel: CrearReporteViewModel,
    onGuardado: (String) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    CrearContent(
        state = state,
        onTituloChange = { viewModel.actualizarTitulo(it) },
        onGuardar = {
            viewModel.guardar()
            state.guardadoId?.let { id -> onGuardado(id) }
        }
    )
}
