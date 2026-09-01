package com.example.miformacionctma.repository

import com.example.miformacionctma.model.Reporte
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ReporteRepository {
    val reportes: StateFlow<List<Reporte>>
    fun agregar(reporte: Reporte)
}

class InMemoryReporteRepository : ReporteRepository {
    private val _reportes = MutableStateFlow<List<Reporte>>(emptyList())
    override val reportes: StateFlow<List<Reporte>> = _reportes

    override fun agregar(reporte: Reporte) {
        _reportes.value = _reportes.value + reporte
    }
}
