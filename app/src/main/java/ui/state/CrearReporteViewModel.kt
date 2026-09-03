package ui.state

import androidx.lifecycle.ViewModel
import com.example.miformacionctma.model.Reporte
import com.example.miformacionctma.model.ReporteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

data class CrearUiState(
    val titulo: String = "",
    val errorTitulo: String? = null,
    val guardando: Boolean = false,
    val guardadoId: String? = null
)

class CrearReporteViewModel(
    private val repository: ReporteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CrearUiState())
    val uiState: StateFlow<CrearUiState> = _uiState.asStateFlow()

    fun actualizarTitulo(valor: String) {
        val nuevo = valor.take(80)
        _uiState.value = _uiState.value.copy(
            titulo = nuevo,
            errorTitulo = if (nuevo.isBlank() || nuevo.length < 4) "Título inválido" else null
        )
    }

    fun guardar() {
        val titulo = _uiState.value.titulo
        if (titulo.isBlank() || titulo.length < 4) {
            _uiState.value = _uiState.value.copy(errorTitulo = "Debe tener al menos 4 caracteres")
            return
        }
        val reporte = Reporte(id = UUID.randomUUID().toString(), titulo = titulo)
        repository.agregar(reporte)
        _uiState.value = _uiState.value.copy(guardadoId = reporte.id)
    }
}
