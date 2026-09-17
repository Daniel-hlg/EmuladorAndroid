package com.example.miformacionctma.data.remote

import com.example.miformacionctma.data.remote.api.ActividadApiService
import com.example.miformacionctma.data.remote.model.ActividadDto
import retrofit2.Response

class RemoteActividadDataSource(
    private val apiService: ActividadApiService
) {
    suspend fun obtenerActividadesRemotas(): Response<List<ActividadDto>> {
        return apiService.obtenerActividades()
    }

    suspend fun enviarActividadRemota(actividad: ActividadDto): Response<ActividadDto> {
        return apiService.crearActividad(actividad)
    }
}