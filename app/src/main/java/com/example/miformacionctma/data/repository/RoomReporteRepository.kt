package com.example.miformacionctma.data.repository

import com.example.miformacionctma.data.local.dao.ReporteDao
import com.example.miformacionctma.data.local.entity.ReporteEntity
import com.example.miformacionctma.data.remote.RemoteActividadDataSource
import com.example.miformacionctma.data.remote.model.toEntity
import com.example.miformacionctma.model.Reporte
import com.example.miformacionctma.model.ReporteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

// Mappers de Dominio <-> Entidad Local
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
    private val remoteDataSource: RemoteActividadDataSource,
    private val externalScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : ReporteRepository {

    // La UI observa esta lista reactiva que proviene de Room
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

    // Estrategia Offline-First: Consulta API REST y actualiza Room de forma atómica
    suspend fun refresh(): Result<Unit> {
        return try {
            val response = remoteDataSource.obtenerActividadesRemotas()
            if (response.isSuccessful) {
                val actividadesDto = response.body() ?: emptyList()
                val entidades = actividadesDto.map { it.toEntity() }
                entidades.forEach { reporteDao.insert(it) }
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error en servidor: ${response.code()}"))
            }
        } catch (e: CancellationException) {
            // Relanza la cancelación para no romper el ciclo de vida de las corrutinas
            throw e
        } catch (e: IOException) {
            // Ante fallo de conexión, Room mantiene la información previa
            Result.failure(Exception("Sin conexión. Mostrando información en caché."))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun agregar(reporte: Reporte) {
        reporteDao.insert(reporte.toEntity())
    }

    override suspend fun actualizar(reporte: Reporte) {
        reporteDao.update(reporte.toEntity())
    }

    override suspend fun eliminar(reporte: Reporte) {
        reporteDao.delete(reporte.toEntity())
    }

    suspend fun eliminarPorId(id: Int) {
        reporteDao.eliminarPorId(id)
    }
}