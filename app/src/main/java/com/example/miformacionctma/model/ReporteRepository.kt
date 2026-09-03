package com.example.miformacionctma.model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ReporteRepository {
    val reportes: StateFlow<List<Reporte>>
    fun agregar(reporte: Reporte)
}

class ReporteRepositoryImpl : ReporteRepository {
    private val _reportes = MutableStateFlow<List<Reporte>>(emptyList())
    override val reportes: StateFlow<List<Reporte>> = _reportes

    override fun agregar(reporte: Reporte) {
        _reportes.value = _reportes.value + reporte
    }
}
