package com.example.miformacionctma.ui.state

import com.example.miformacionctma.model.Reporte
import com.example.miformacionctma.model.ReporteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FakeReporteRepository : ReporteRepository {
    private val _reportes = MutableStateFlow<List<Reporte>>(emptyList())
    override val reportes: StateFlow<List<Reporte>> = _reportes.asStateFlow()

    override suspend fun agregar(reporte: Reporte) {
        _reportes.value = _reportes.value + reporte
    }

    override suspend fun actualizar(reporte: Reporte) {
        _reportes.value = _reportes.value.map {
            if (it.id == reporte.id) reporte else it
        }
    }

    override suspend fun eliminar(reporte: Reporte) {
        _reportes.value = _reportes.value.filter { it.id != reporte.id }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class CrearReporteViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var fakeRepository: FakeReporteRepository
    private lateinit var viewModel: CrearReporteViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeReporteRepository()
        viewModel = CrearReporteViewModel(fakeRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `HU1 - Reporte nuevo tiene progreso 0 por ciento`() = runTest {
        // Iniciamos la recolección en segundo plano para activar el StateFlow (SharingStarted.WhileSubscribed)
        val collectJob = backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.listadoUiState.collect {}
        }
        
        val reporte = Reporte("1", "Test", "Hoy", completado = false)
        fakeRepository.agregar(reporte)
        
        advanceUntilIdle()
        
        val state = viewModel.listadoUiState.value
        assertTrue("El estado debería ser Contenido, pero es: $state", state is ListadoUiState.Contenido)
        val uiState = (state as ListadoUiState.Contenido).lista.first()
        assertEquals(0.0f, uiState.progreso)
        assertEquals("Pendiente", uiState.estado)
        
        collectJob.cancel()
    }

    @Test
    fun `HU1 - Reporte completado tiene progreso 100 por ciento`() = runTest {
        val collectJob = backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.listadoUiState.collect {}
        }
        
        val reporte = Reporte("1", "Test", "Hoy", completado = true)
        fakeRepository.agregar(reporte)
        
        advanceUntilIdle()
        
        val state = viewModel.listadoUiState.value
        assertTrue("El estado debería ser Contenido, pero es: $state", state is ListadoUiState.Contenido)
        val uiState = (state as ListadoUiState.Contenido).lista.first()
        assertEquals(100.0f, uiState.progreso)
        assertEquals("Completada", uiState.estado)
        
        collectJob.cancel()
    }

    @Test
    fun `HU2 - Marcar actividad como completada actualiza el estado`() = runTest {
        val collectJob = backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.listadoUiState.collect {}
        }
        
        val reporte = Reporte("1", "Test", "Hoy", completado = false)
        fakeRepository.agregar(reporte)
        advanceUntilIdle()
        
        viewModel.completarActividad(1)
        advanceUntilIdle()
        
        val reporteEnRepo = fakeRepository.reportes.value.first()
        assertTrue("El reporte en el repositorio debería estar completado", reporteEnRepo.completado)
        
        val state = viewModel.listadoUiState.value
        assertTrue("El estado debería ser Contenido", state is ListadoUiState.Contenido)
        val uiState = (state as ListadoUiState.Contenido).lista.first()
        assertEquals(100.0f, uiState.progreso)
        assertEquals("Completada", uiState.estado)
        
        collectJob.cancel()
    }

    @Test
    fun `HU3 - El catalogo filtra correctamente por titulo`() = runTest {
        val collectJob = backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.listadoUiState.collect {}
        }
        
        fakeRepository.agregar(Reporte("1", "Kotlin", "Hoy"))
        fakeRepository.agregar(Reporte("2", "Scrum", "Hoy"))
        advanceUntilIdle()
        
        viewModel.actualizarBusqueda("Kot")
        advanceUntilIdle()
        
        val state = viewModel.listadoUiState.value
        assertTrue("El estado debería ser Contenido", state is ListadoUiState.Contenido)
        val lista = (state as ListadoUiState.Contenido).lista
        assertEquals("Debería haber solo 1 actividad en la lista filtrada", 1, lista.size)
        assertEquals("Kotlin", lista.first().titulo)
        
        collectJob.cancel()
    }
}
