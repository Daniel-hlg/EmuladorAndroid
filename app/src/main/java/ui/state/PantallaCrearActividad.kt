package com.example.miformacionctma.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.ui.components.FormularioActividad
import com.example.miformacionctma.ui.state.FormularioActividadUiState

@Composable
fun PantallaCrearActividad(
    onGuardarActividad: (FormularioActividadUiState) -> Unit
) {

    var uiState by rememberSaveable {
        mutableStateOf(
            FormularioActividadUiState()
        )
    }

    // -----------------------------
    // VALIDAR TÍTULO
    // -----------------------------

    fun validarTitulo(titulo: String): String? {

        return when {
            titulo.isBlank() ->
                "El título es obligatorio"

            titulo.length < 3 ->
                "Debe tener al menos 3 caracteres"

            titulo.length > 80 ->
                "No puede superar 80 caracteres"

            else -> null
        }
    }

    // -----------------------------
    // VALIDAR DESCRIPCIÓN
    // -----------------------------

    fun validarDescripcion(
        descripcion: String
    ): String? {

        return if (descripcion.length > 240) {
            "La descripción no puede superar 240 caracteres"
        } else {
            null
        }
    }

    // -----------------------------
    // VALIDAR FECHA
    // -----------------------------

    fun validarFecha(
        fecha: String
    ): String? {

        return if (fecha.isBlank()) {
            "La fecha es obligatoria"
        } else {
            null
        }
    }

    // -----------------------------
    // VALIDAR PROGRESO
    // -----------------------------

    fun validarProgreso(
        progreso: Int
    ): String? {

        return if (progreso !in 0..100) {
            "El progreso debe estar entre 0 y 100"
        } else {
            null
        }
    }

    // -----------------------------
    // ACTUALIZAR TÍTULO
    // -----------------------------

    fun actualizarTitulo(
        nuevoTitulo: String
    ) {

        val error = validarTitulo(nuevoTitulo)

        val nuevosErrores =
            uiState.errores.toMutableMap()

        if (error != null) {
            nuevosErrores["titulo"] = error
        } else {
            nuevosErrores.remove("titulo")
        }

        uiState = uiState.copy(
            titulo = nuevoTitulo,
            errores = nuevosErrores
        )
    }

    // -----------------------------
    // ACTUALIZAR DESCRIPCIÓN
    // -----------------------------

    fun actualizarDescripcion(
        nuevaDescripcion: String
    ) {

        val error =
            validarDescripcion(nuevaDescripcion)

        val nuevosErrores =
            uiState.errores.toMutableMap()

        if (error != null) {
            nuevosErrores["descripcion"] = error
        } else {
            nuevosErrores.remove("descripcion")
        }

        uiState = uiState.copy(
            descripcion = nuevaDescripcion,
            errores = nuevosErrores
        )
    }

    // -----------------------------
    // ACTUALIZAR FECHA
    // -----------------------------

    fun actualizarFecha(
        nuevaFecha: String
    ) {

        val error =
            validarFecha(nuevaFecha)

        val nuevosErrores =
            uiState.errores.toMutableMap()

        if (error != null) {
            nuevosErrores["fecha"] = error
        } else {
            nuevosErrores.remove("fecha")
        }

        uiState = uiState.copy(
            fecha = nuevaFecha,
            errores = nuevosErrores
        )
    }

    // -----------------------------
    // ACTUALIZAR PROGRESO
    // -----------------------------

    fun actualizarProgreso(
        nuevoProgreso: Int
    ) {

        val error =
            validarProgreso(nuevoProgreso)

        val nuevosErrores =
            uiState.errores.toMutableMap()

        if (error != null) {
            nuevosErrores["progreso"] = error
        } else {
            nuevosErrores.remove("progreso")
        }

        uiState = uiState.copy(
            progreso = nuevoProgreso,
            errores = nuevosErrores
        )
    }

    // -----------------------------
    // COMPROBAR SI PUEDE GUARDAR
    // -----------------------------

    fun actualizarPuedeGuardar() {

        val tituloValido =
            validarTitulo(uiState.titulo) == null

        val descripcionValida =
            validarDescripcion(uiState.descripcion) == null

        val fechaValida =
            validarFecha(uiState.fecha) == null

        val progresoValido =
            validarProgreso(uiState.progreso) == null

        uiState = uiState.copy(
            puedeGuardar =
                tituloValido &&
                        descripcionValida &&
                        fechaValida &&
                        progresoValido &&
                        uiState.errores.isEmpty()
        )
    }

    // -----------------------------
    // PANTALLA
    // -----------------------------

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        FormularioActividad(
            uiState = uiState,

            onTituloChange = {
                actualizarTitulo(it)
                actualizarPuedeGuardar()
            },

            onDescripcionChange = {
                actualizarDescripcion(it)
                actualizarPuedeGuardar()
            },

            onFechaChange = {
                actualizarFecha(it)
                actualizarPuedeGuardar()
            },

            onPrioridadChange = {
                uiState = uiState.copy(
                    prioridad = it
                )
                actualizarPuedeGuardar()
            },

            onProgresoChange = {
                actualizarProgreso(it)
                actualizarPuedeGuardar()
            },

            onGuardar = {
                if (uiState.puedeGuardar) {
                    onGuardarActividad(uiState)
                }
            }
        )
    }
}

