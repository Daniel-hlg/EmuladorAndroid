package com.example.miformacionctma.model

data class Reporte(
    val id: String,
    val titulo: String,
    val fecha: String,
    val completado: Boolean = false,
    val resuelto: Boolean = false,
    val fotoUri: String? = null // <- Agregado para Semana 9
)