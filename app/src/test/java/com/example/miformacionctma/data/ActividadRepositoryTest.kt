package com.example.miformacionctma.data

import com.example.miformacionctma.FakeReporteRepository
import com.example.miformacionctma.model.Reporte
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ActividadRepositoryTest {

    @Test
    fun alAgregarReporte_seActualizaLaListaEnElRepository() = runTest {
        // 1. Instanciar el Fake
        val repository = FakeReporteRepository()
        val nuevoReporte = Reporte(
            id = "1",
            titulo = "Reporte de Prueba",
            fecha = "2026-09-17"
        )

        // 2. Ejecutar la acción suspendida
        repository.agregar(nuevoReporte)

        // 3. Obtener el valor actual del StateFlow
        val listaActual = repository.reportes.value

        // 4. Verificar que la lista contenga el reporte agregado
        assertEquals(1, listaActual.size)
        assertEquals("Reporte de Prueba", listaActual.first().titulo)
    }
}