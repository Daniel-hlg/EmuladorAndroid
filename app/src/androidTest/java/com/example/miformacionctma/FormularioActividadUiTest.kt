package com.example.miformacionctma

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.miformacionctma.ui.state.FormularioActividad
import com.example.miformacionctma.ui.state.FormularioActividadUiState
import org.junit.Rule
import org.junit.Test

class FormularioActividadUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun verificarCamposyBotonesMultimediaVisibles() {
        composeTestRule.setContent {
            FormularioActividad(
                uiState = FormularioActividadUiState(),
                onTituloChange = {},
                onDescripcionChange = {},
                onFechaChange = {},
                onFotoUriChange = {},
                onGuardarClick = {}
            )
        }

        // Validar que los textos y botones multimedia se renderizan correctamente
        composeTestRule.onNodeWithText("Evidencia Fotográfica").assertIsDisplayed()
        composeTestRule.onNodeWithText("Galería").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cámara").assertIsDisplayed()
        composeTestRule.onNodeWithText("Guardar Actividad").assertIsDisplayed()
    }
}