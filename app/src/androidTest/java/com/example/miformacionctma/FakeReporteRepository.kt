package com.example.miformacionctma

import com.example.miformacionctma.model.Reporte
import com.example.miformacionctma.model.ReporteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
class FakeReporteRepository : ReporteRepository {

    private val _listaReportes = MutableStateFlow<List<Reporte>>(emptyList())
    override val reportes: StateFlow<List<Reporte>> = _listaReportes.asStateFlow()

    override suspend fun agregar(reporte: Reporte) {
        val listaActual = _listaReportes.value.toMutableList()
        listaActual.add(reporte)
        _listaReportes.value = listaActual
    }

    override suspend fun actualizar(reporte: Reporte) {
        val listaActual = _listaReportes.value.toMutableList()
        val index = listaActual.indexOfFirst { it.id == reporte.id }
        if (index != -1) {
            listaActual[index] = reporte
            _listaReportes.value = listaActual
        }
    }

    override suspend fun eliminar(reporte: Reporte) {
        val listaActual = _listaReportes.value.toMutableList()
        listaActual.removeIf { it.id == reporte.id }
        _listaReportes.value = listaActual
    }
}
