package com.example.miformacionctma.model

import kotlinx.coroutines.flow.StateFlow

interface ReporteRepository {
    val reportes: StateFlow<List<Reporte>>

    suspend fun agregar(reporte: Reporte)
    suspend fun actualizar(reporte: Reporte)
    suspend fun eliminar(reporte: Reporte)
}