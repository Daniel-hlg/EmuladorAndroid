package com.example.miformacionctma.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.miformacionctma.MiFormacionApplication
import com.example.miformacionctma.model.Reporte
import com.example.miformacionctma.model.ReporteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CrearReporteViewModel(
    private val reporteRepository: ReporteRepository
) : ViewModel() {

    val listaActividadesState: StateFlow<List<FormularioActividadUiState>> = reporteRepository.reportes
        .map { lista ->
            lista.map { reporte ->
                FormularioActividadUiState(
                    id = reporte.id,
                    titulo = reporte.titulo,
                    fecha = reporte.fecha,
                    estado = if (reporte.completado) "Completada" else "Pendiente",
                    progreso = if (reporte.completado) 1.0f else 0.0f
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun guardarReporte(titulo: String, fecha: String) {
        if (titulo.isBlank()) return
        viewModelScope.launch {
            val nuevoReporte = Reporte(
                id = "0",
                titulo = titulo,
                fecha = if (fecha.isBlank()) "Fecha no especificada" else fecha,
                completado = false,
                resuelto = false
            )
            reporteRepository.agregar(nuevoReporte)
        }
    }

    fun marcarComoCompletada(actividadUi: FormularioActividadUiState) {
        viewModelScope.launch {
            val reporteActualizado = Reporte(
                id = actividadUi.id,
                titulo = actividadUi.titulo,
                fecha = actividadUi.fecha,
                completado = true,
                resuelto = true
            )
            reporteRepository.actualizar(reporteActualizado)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MiFormacionApplication)
                CrearReporteViewModel(application.container.reporteRepository)
            }
        }
    }
}