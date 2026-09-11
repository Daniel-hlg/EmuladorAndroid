package com.example.miformacionctma.ui.state

import com.example.miformacionctma.model.ActividadFormativa

// Estado visual principal para el listado de actividades
sealed interface ListadoUiState {
    object Cargando : ListadoUiState
    data class Contenido(val lista: List<FormularioActividadUiState>) : ListadoUiState
    object Vacio : ListadoUiState
    data class Error(val mensaje: String) : ListadoUiState
}

// Estado para operaciones puntuales (Guardar / Eliminar)
sealed interface OperacionUiState {
    object Inactiva : OperacionUiState
    object EnCurso : OperacionUiState
    object Exitosa : OperacionUiState
    data class Fallida(val mensaje: String) : OperacionUiState
}