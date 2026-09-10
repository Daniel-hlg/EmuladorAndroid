package com.example.miformacionctma.data.repository

import com.example.miformacionctma.data.local.dao.ReporteDao
import com.example.miformacionctma.data.local.entity.ReporteEntity
import com.example.miformacionctma.model.Reporte
import com.example.miformacionctma.model.ReporteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

fun ReporteEntity.toDomain(): Reporte = Reporte(
    id = id.toString(),
    titulo = titulo,
    fecha = fecha,
    completado = completado,
    resuelto = resuelto
)

fun Reporte.toEntity(): ReporteEntity = ReporteEntity(
    id = id.toIntOrNull() ?: 0,
    titulo = titulo,
    fecha = fecha,
    completado = completado,
    resuelto = resuelto
)

class RoomReporteRepository(
    private val reporteDao: ReporteDao,
    private val externalScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : ReporteRepository {

    override val reportes: StateFlow<List<Reporte>> = reporteDao
        .obtenerTodos()
        .map { listaEntidades ->
            listaEntidades.map { it.toDomain() }
        }
        .stateIn(
            scope = externalScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    override suspend fun agregar(reporte: Reporte) {
        reporteDao.insert(reporte.toEntity())
    }

    override suspend fun actualizar(reporte: Reporte) {
        reporteDao.update(reporte.toEntity())
    }

    override suspend fun eliminar(reporte: Reporte) {
        reporteDao.delete(reporte.toEntity())
    }
}