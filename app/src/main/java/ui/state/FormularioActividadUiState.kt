package com.example.miformacionctma.ui.state

data class FormularioActividadUiState(
    val id: String = "",
    val titulo: String = "",
    val descripcion: String = "",
    val fecha: String = "",
    val estado: String = "Pendiente",
    val progreso: Float = 0.0f,
    val fotoUri: String? = null, // <- Agregado para Semana 9 (evidencia multimedia)
    val errores: Map<String, String> = emptyMap()
)