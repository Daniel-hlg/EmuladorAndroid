package com.example.miformacionctma.ui.state

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import java.io.File

@Composable
fun FormularioActividad(
    uiState: FormularioActividadUiState,
    onTituloChange: (String) -> Unit,
    onDescripcionChange: (String) -> Unit,
    onFechaChange: (String) -> Unit,
    onFotoUriChange: (String?) -> Unit = {},
    onGuardarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var tempUri by remember { mutableStateOf<Uri?>(null) }

    // Helper para generar una URI temporal segura
    fun crearTempUri(): Uri? {
        return try {
            val directory = context.externalCacheDir ?: context.cacheDir
            if (!directory.exists()) {
                directory.mkdirs()
            }
            val tempFile = File.createTempFile("evidencia_", ".jpg", directory)
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                tempFile
            )
        } catch (e: Exception) {
            Log.e("FormularioActividad", "Error creando archivo/URI temporal para cámara: ${e.message}", e)
            null
        }
    }

    // Launcher para capturar foto desde la Cámara
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { exito ->
        if (exito && tempUri != null) {
            onFotoUriChange(tempUri.toString())
        }
    }

    // Función interna para iniciar el proceso de toma de foto
    fun tomarFoto() {
        val uri = crearTempUri()
        if (uri != null) {
            tempUri = uri
            cameraLauncher.launch(uri)
        } else {
            Log.e("FormularioActividad", "No se pudo generar la Uri temporal.")
        }
    }

    // Launcher para solicitar el permiso de cámara en tiempo de ejecución
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { concedido ->
        if (concedido) {
            tomarFoto()
        } else {
            Log.w("FormularioActividad", "Permiso de cámara denegado por el usuario.")
        }
    }

    // Launcher para seleccionar foto desde la Galería
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            try {
                context.contentResolver.takePersistableUriPermission(
                    it, Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (_: Exception) {}
            onFotoUriChange(it.toString())
        }
    }

    Column(modifier = modifier.padding(16.dp)) {
        // Campo TÍTULO
        OutlinedTextField(
            value = uiState.titulo,
            onValueChange = onTituloChange,
            label = { Text("Título") },
            isError = uiState.errores.containsKey("titulo"),
            supportingText = {
                uiState.errores["titulo"]?.let { Text(it) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo DESCRIPCIÓN
        OutlinedTextField(
            value = uiState.descripcion,
            onValueChange = onDescripcionChange,
            label = { Text("Descripción") },
            isError = uiState.errores.containsKey("descripcion"),
            supportingText = {
                uiState.errores["descripcion"]?.let { Text(it) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo FECHA
        OutlinedTextField(
            value = uiState.fecha,
            onValueChange = onFechaChange,
            label = { Text("Fecha (ej. 15 de agosto)") },
            isError = uiState.errores.containsKey("fecha"),
            supportingText = {
                uiState.errores["fecha"]?.let { Text(it) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // SECCIÓN DE EVIDENCIA MULTIMEDIA
        Text("Evidencia Fotográfica")
        Spacer(modifier = Modifier.height(6.dp))

        if (!uiState.fotoUri.isNullOrBlank()) {
            AsyncImage(
                model = uiState.fotoUri,
                contentDescription = "Evidencia de la actividad",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = {
                    galleryLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Galería")
            }

            OutlinedButton(
                onClick = {
                    val hasPermission = ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED

                    if (hasPermission) {
                        tomarFoto()
                    } else {
                        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cámara")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón GUARDAR
        Button(
            onClick = onGuardarClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Actividad")
        }
    }
}