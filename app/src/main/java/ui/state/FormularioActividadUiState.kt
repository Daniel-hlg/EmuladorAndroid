package com.example.miformacionctma.ui.state

data class FormularioActividadUiState(
    val titulo: String = "",
    val descripcion: String = "",
    val fecha: String = "",
    val prioridad: Int = 0,
    val progreso: Int = 0,
    val errores: Map<String, String> = emptyMap(),
    val puedeGuardar: Boolean = false
)
