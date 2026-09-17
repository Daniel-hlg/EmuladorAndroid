package com.example.miformacionctma.data.remote.model

import com.example.miformacionctma.data.local.entity.ReporteEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActividadDto(
    @SerialName("id") val id: Int,
    @SerialName("titulo") val titulo: String,
    @SerialName("fecha") val fecha: String,
    @SerialName("completado") val completado: Boolean,
    @SerialName("resuelto") val resuelto: Boolean
)

fun ActividadDto.toEntity(): ReporteEntity {
    return ReporteEntity(
        id = this.id,
        titulo = this.titulo,
        fecha = this.fecha,
        completado = this.completado,
        resuelto = this.resuelto
    )
}