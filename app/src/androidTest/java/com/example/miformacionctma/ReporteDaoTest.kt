package com.example.miformacionctma

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.miformacionctma.data.local.AppDatabase
import com.example.miformacionctma.data.local.dao.ReporteDao
import com.example.miformacionctma.data.local.entity.ReporteEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ReporteDaoTest {

    private lateinit var reporteDao: ReporteDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        reporteDao = db.reporteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertarYObtenerReportes() = runBlocking {
        val reporte = ReporteEntity(
            id = 1,
            titulo = "Reporte de prueba UI",
            fecha = "10 de septiembre",
            completado = false,
            resuelto = true
        )

        reporteDao.insert(reporte)

        val listaReportes = reporteDao.obtenerTodos().first()

        assertEquals(1, listaReportes.size)
        assertEquals("Reporte de prueba UI", listaReportes[0].titulo)
        assertTrue(listaReportes[0].resuelto)
    }
}