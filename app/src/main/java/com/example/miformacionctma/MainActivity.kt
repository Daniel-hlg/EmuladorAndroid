package com.example.miformacionctma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.miformacionctma.ui.state.CrearReporteViewModel
import com.example.miformacionctma.ui.state.PantallaCrearReporte
import com.example.miformacionctma.ui.theme.MiFormacionCTMATheme
import com.example.miformacionctma.uii.screens.PantallaActividades

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MiFormacionCTMATheme {
                val crearReporteViewModel: CrearReporteViewModel = viewModel(
                    factory = CrearReporteViewModel.Factory
                )

                var pantallaActual by remember { mutableStateOf("lista") }

                when (pantallaActual) {
                    "lista" -> {
                        PantallaActividades(
                            viewModel = crearReporteViewModel,
                            onNavegarACrearActividad = {
                                pantallaActual = "crear"
                            }
                        )
                    }
                    "crear" -> {
                        PantallaCrearReporte(
                            viewModel = crearReporteViewModel,
                            onReporteCreado = {
                                pantallaActual = "lista"
                            },
                            onNavegarAtras = {
                                pantallaActual = "lista"
                            }
                        )
                    }
                }
            }
        }
    }
}