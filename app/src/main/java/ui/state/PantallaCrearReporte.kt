package ui.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun CrearRoute(
    viewModel: CrearReporteViewModel,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CrearContent(
        uiState = uiState,
        onTituloChange = viewModel::actualizarTitulo,
        onGuardar = {
            viewModel.guardar()
            // Si ya se guardó, volvemos a la listaa
            if (uiState.guardadoId != null) {
                navController.popBackStack()
            }
        }
    )
}

@Composable
fun CrearContent(
    uiState: CrearUiState,
    onTituloChange: (String) -> Unit,
    onGuardar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = uiState.titulo,
            onValueChange = onTituloChange,
            label = { Text("Título") },
            isError = uiState.errorTitulo != null,
            supportingText = { uiState.errorTitulo?.let { Text(it) } }
        )
        Button(
            onClick = onGuardar,
            enabled = uiState.errorTitulo == null && uiState.titulo.isNotBlank()
        ) {
            Text("Guardar")
        }
    }
}
