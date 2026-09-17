package com.example.miformacionctma.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.miformacionctma.data.local.dao.ReporteDao
import com.example.miformacionctma.data.local.entity.ReporteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ReporteEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun reporteDao(): ReporteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Migración explícita de Versión 1 a Versión 2 (Añade columna fotoUri)
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE reportes ADD COLUMN fotoUri TEXT DEFAULT NULL")
            }
        }

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "miformacion_database"
                )
                    .addMigrations(MIGRATION_1_2) // Aplica la migración explícita
                    .fallbackToDestructiveMigration() // Respaldo de seguridad en caso de fallas
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.reporteDao())
                }
            }
        }

        suspend fun populateDatabase(reporteDao: ReporteDao) {
            val actividadesIniciales = listOf(
                ReporteEntity(titulo = "Manifiesto Ágil", fecha = "11 de agosto", completado = true, resuelto = true),
                ReporteEntity(titulo = "Valores del Manifiesto Ágil", fecha = "12 de agosto", completado = true, resuelto = true),
                ReporteEntity(titulo = "Principios Ágiles", fecha = "13 de agosto", completado = true, resuelto = true),
                ReporteEntity(titulo = "Introducción a Scrum", fecha = "14 de agosto", completado = false, resuelto = false),
                ReporteEntity(titulo = "Roles de Scrum", fecha = "15 de agosto", completado = false, resuelto = false),
                ReporteEntity(titulo = "Artefactos de Scrum", fecha = "16 de agosto", completado = false, resuelto = false),
                ReporteEntity(titulo = "Pruebas de software", fecha = "17 de agosto", completado = false, resuelto = false),
                ReporteEntity(titulo = "Tipos de pruebas", fecha = "18 de agosto", completado = false, resuelto = false),
                ReporteEntity(titulo = "Jetpack Compose", fecha = "19 de agosto", completado = false, resuelto = false),
                ReporteEntity(titulo = "Proyecto Mi Formación CTMA", fecha = "20 de agosto", completado = false, resuelto = false)
            )

            actividadesIniciales.forEach { reporteDao.insert(it) }
        }
    }
}