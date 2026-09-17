package com.example.miformacionctma.uii.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.miformacionctma.FakeReporteRepository
import com.example.miformacionctma.ui.state.CrearReporteViewModel
import com.example.miformacionctma.ui.theme.MiFormacionCTMATheme
import org.junit.Rule
import org.junit.Test

class PantallaActividadesTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun hu3_abrirCatalogo_seMuestraCabeceraYFiltro() {
        // Creamos un repositorio falso limpio para el entorno del test
        val fakeRepository = FakeReporteRepository()
        // Inicializamos el ViewModel pasando el repositorio mockeado
        val viewModel = CrearReporteViewModel(fakeRepository)

        composeTestRule.setContent {
            MiFormacionCTMATheme {
                PantallaActividades(
                    viewModel = viewModel
                )
            }
        }

        // Verificar que el título de la app y de la pantalla están presentes
        composeTestRule.onNodeWithText("Mi Formación CTMA").assertIsDisplayed()
        composeTestRule.onNodeWithText("Actividades formativas").assertIsDisplayed()
        
        // Verificar que el campo de búsqueda existe
        composeTestRule.onNodeWithText("Buscar actividad...").assertIsDisplayed()
    }
}
