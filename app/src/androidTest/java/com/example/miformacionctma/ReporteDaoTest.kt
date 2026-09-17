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

    @Test
    fun eliminarReportePorId() = runBlocking {
        // Arrange
        val reporte = ReporteEntity(
            id = 2,
            titulo = "Reporte a eliminar",
            fecha = "15 de septiembre",
            completado = false,
            resuelto = false
        )
        reporteDao.insert(reporte)

        // Act
        reporteDao.eliminarPorId(2)

        // Assert
        val listaReportes = reporteDao.obtenerTodos().first()
        assertTrue("La lista debería estar vacía tras eliminar", listaReportes.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun insertarYObtenerReporteConFotoUri() = runBlocking {
        // Arrange
        val uriEsperada = "content://media/external/images/media/100"
        val reporte = ReporteEntity(
            id = 3,
            titulo = "Reporte con Evidencia",
            fecha = "17 de septiembre",
            completado = false,
            resuelto = false,
            fotoUri = uriEsperada
        )

        // Act
        reporteDao.insert(reporte)

        // Assert
        val listaReportes = reporteDao.obtenerTodos().first()
        val reporteGuardado = listaReportes.first { it.id == 3 }

        assertEquals(uriEsperada, reporteGuardado.fotoUri)
    }
}