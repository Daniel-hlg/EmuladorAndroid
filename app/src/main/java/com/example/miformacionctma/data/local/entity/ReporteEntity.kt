package com.example.miformacionctma.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reportes")
data class ReporteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val fecha: String,
    val completado: Boolean = false,

    // Nueva columna agregada en la Versión 2
    @ColumnInfo(name = "resuelto", defaultValue = "0")
    val resuelto: Boolean = false
)