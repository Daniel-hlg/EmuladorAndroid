package com.example.miformacionctma.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ReporteConCategoria(
    @Embedded
    val reporte: ReporteEntity,

    @Relation(
        parentColumn = "categoriaId",
        entityColumn = "id"
    )
    val categoria: CategoriaEntity?
)