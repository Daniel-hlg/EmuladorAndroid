package com.example.miformacionctma.data.remote.api

import com.example.miformacionctma.data.remote.model.ActividadDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface ActividadApiService {

    @GET("v1/actividades")
    suspend fun obtenerActividades(): Response<List<ActividadDto>>

    @POST("v1/actividades")
    suspend fun crearActividad(@Body actividad: ActividadDto): Response<ActividadDto>
}