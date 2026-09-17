package com.example.miformacionctma.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.miformacionctma.MiFormacionApplication
import com.example.miformacionctma.data.repository.RoomReporteRepository
import com.example.miformacionctma.model.Reporte
import com.example.miformacionctma.model.ReporteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CrearReporteViewModel(
    private val repository: ReporteRepository
) : ViewModel() {

    private val _operacionState = MutableStateFlow<OperacionUiState>(OperacionUiState.Inactiva)
    val operacionState: StateFlow<OperacionUiState> = _operacionState.asStateFlow()

    private val _textoBusqueda = MutableStateFlow("")
    val textoBusqueda: StateFlow<String> = _textoBusqueda.asStateFlow()

    // Combina la propiedad 'repository.reportes' con el texto de búsqueda
    val listadoUiState: StateFlow<ListadoUiState> = combine(
        repository.reportes,
        _textoBusqueda
    ) { listaReportes, busqueda ->
        val listaFiltrada = if (busqueda.isBlank()) {
            listaReportes
        } else {
            listaReportes.filter { reporte ->
                reporte.titulo.contains(busqueda, ignoreCase = true)
            }
        }

        if (listaFiltrada.isEmpty()) {
            ListadoUiState.Vacio
        } else {
            val listaUi = listaFiltrada.map { reporte ->
                FormularioActividadUiState(
                    id = reporte.id,
                    titulo = reporte.titulo,
                    descripcion = "",
                    fecha = reporte.fecha,
                    estado = if (reporte.completado) "Completada" else "Pendiente",
                    progreso = if (reporte.completado) 100.0f else 0.0f,
                    fotoUri = reporte.fotoUri // <- Mapeo de fotoUri a la UI
                )
            }
            ListadoUiState.Contenido(listaUi)
        }
    }
        .catch { e ->
            emit(ListadoUiState.Error(e.message ?: "Error al cargar los datos"))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ListadoUiState.Cargando
        )

    fun actualizarBusqueda(query: String) {
        _textoBusqueda.value = query
    }

    // Sincronización remota para la Semana 8 (Offline-First)
    fun sincronizarConServidor() {
        viewModelScope.launch {
            _operacionState.value = OperacionUiState.EnCurso
            val roomRepo = repository as? RoomReporteRepository
            if (roomRepo != null) {
                val resultado = roomRepo.refresh()
                resultado.fold(
                    onSuccess = {
                        _operacionState.value = OperacionUiState.Exitosa
                    },
                    onFailure = { error ->
                        _operacionState.value = OperacionUiState.Fallida(error.message ?: "Error al sincronizar")
                    }
                )
            } else {
                _operacionState.value = OperacionUiState.Fallida("Repositorio no compatible con sincronización remota")
            }
        }
    }

    fun guardarReporte(titulo: String, fecha: String, descripcion: String = "", fotoUri: String? = null) {
        viewModelScope.launch {
            _operacionState.value = OperacionUiState.EnCurso
            try {
                val nuevoReporte = Reporte(
                    id = "0",
                    titulo = titulo,
                    fecha = fecha,
                    completado = false,
                    resuelto = false,
                    fotoUri = fotoUri // <- Persistencia de la URI en Room
                )
                repository.agregar(nuevoReporte)
                _operacionState.value = OperacionUiState.Exitosa
            } catch (e: Exception) {
                _operacionState.value = OperacionUiState.Fallida(e.message ?: "Error al guardar")
            }
        }
    }

    fun completarActividad(id: Int) {
        viewModelScope.launch {
            val reporteActual = repository.reportes.value.find { it.id == id.toString() }
            reporteActual?.let {
                val reporteActualizado = it.copy(completado = true)
                repository.actualizar(reporteActualizado)
            }
        }
    }

    fun reiniciarEstadoOperacion() {
        _operacionState.value = OperacionUiState.Inactiva
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MiFormacionApplication)
                val repository = application.container.reporteRepository
                CrearReporteViewModel(repository = repository)
            }
        }
    }
}