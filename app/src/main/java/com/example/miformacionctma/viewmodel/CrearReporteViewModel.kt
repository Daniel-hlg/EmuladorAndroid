package com.example.miformacionctma.viewmodel

import androidx.lifecycle.ViewModel
import com.example.miformacionctma.model.Reporte
import com.example.miformacionctma.repository.ReporteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

data class CrearUiState(
    val titulo: String = "",
    val errorTitulo: String? = null,
    val guardando: Boolean = false,
    val guardadoId: String? = null
)

class CrearReporteViewModel(
    private val repository: ReporteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CrearUiState())
    val uiState: StateFlow<CrearUiState> = _uiState.asStateFlow()

    fun actualizarTitulo(valor: String) {
        val nuevo = valor.take(80)
        _uiState.value = _uiState.value.copy(
            titulo = nuevo,
            errorTitulo = if (nuevo.isBlank() || nuevo.length < 4) "El título debe tener al menos 4 caracteres" else null
        )
    }

    fun guardar() {
        val tituloActual = _uiState.value.titulo.trim()
        if (tituloActual.isBlank() || tituloActual.length < 4) {
            _uiState.value = _uiState.value.copy(
                errorTitulo = "El título no puede estar vacío y debe tener mínimo 4 caracteres"
            )
            return
        }
        val nuevoReporte = Reporte(UUID.randomUUID().toString(), tituloActual)
        repository.agregar(nuevoReporte)
        _uiState.value = _uiState.value.copy(
            guardadoId = nuevoReporte.id,
            guardando = false,
            errorTitulo = null
        )
    }
}
