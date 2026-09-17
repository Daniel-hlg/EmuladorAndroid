package com.example.miformacionctma.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.miformacionctma.data.local.entity.ReporteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReporteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reporte: ReporteEntity)

    @Update
    suspend fun update(reporte: ReporteEntity)

    @Delete
    suspend fun delete(reporte: ReporteEntity)

    @Query("SELECT * FROM reportes ORDER BY id ASC")
    fun obtenerTodos(): Flow<List<ReporteEntity>>

    @Query("SELECT * FROM reportes WHERE id = :id")
    fun obtenerPorId(id: Int): Flow<ReporteEntity?>

    @Query("DELETE FROM reportes WHERE id = :id")
    suspend fun eliminarPorId(id: Int)
}