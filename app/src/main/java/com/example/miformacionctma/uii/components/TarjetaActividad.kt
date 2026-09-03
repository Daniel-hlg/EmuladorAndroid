package com.example.miformacionctma.uii.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.model.ActividadFormativa
import com.example.miformacionctma.ui.theme.MiFormacionCTMATheme


@Composable
fun TarjetaActividad(
    actividad: ActividadFormativa,
    onCompletar: () -> Unit,
    onActividadClick: (Int) -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onActividadClick(actividad.id)
            }
            .semantics {
                contentDescription =
                    "Actividad ${actividad.titulo}, " +
                            "estado ${actividad.estado}, " +
                            "progreso ${actividad.progreso} por ciento. " +
                            "Toca para ver el detalle."
            }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // TÍTULO

            Text(
                text = actividad.titulo,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )


            // DESCRIPCIÓN

            Text(
                text = actividad.descripcion,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )


            // FECHA + ESTADO

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Fecha: ${actividad.fecha}",
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = actividad.estado,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )


            // PROGRESO

            Text(
                text = "Progreso: ${actividad.progreso}%"
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            LinearProgressIndicator(
                progress = {
                    actividad.progreso / 100f
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )


            // BOTÓN COMPLETAR

            Button(
                onClick = onCompletar,
                enabled = actividad.estado != "Completada",
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        contentDescription =
                            if (actividad.estado == "Completada") {
                                "Actividad ${actividad.titulo} completada"
                            } else {
                                "Completar actividad ${actividad.titulo}"
                            }
                    }
            ) {

                Text(
                    text = if (actividad.estado == "Completada") {
                        "Actividad completada"
                    } else {
                        "Completar actividad"
                    }
                )
            }
        }
    }
}


/* =========================================================
   PREVIEW 1
   Actividad completada
   ========================================================= */

@Preview(
    showBackground = true,
    name = "Actividad completada"
)
@Composable
fun TarjetaActividadCompletadaPreview() {

    MiFormacionCTMATheme {

        TarjetaActividad(
            actividad = ActividadFormativa(
                id = 1,
                titulo = "Introducción a Kotlin",
                descripcion = "Actividad completada sobre los conceptos básicos de Kotlin.",
                fecha = "11 de agosto",
                estado = "Completada",
                progreso = 100
            ),
            onCompletar = {}
        )
    }
}


/* =========================================================
   PREVIEW 2
   Actividad en proceso
   ========================================================= */

@Preview(
    showBackground = true,
    name = "Actividad en proceso"
)
@Composable
fun TarjetaActividadEnProcesoPreview() {

    MiFormacionCTMATheme {

        TarjetaActividad(
            actividad = ActividadFormativa(
                id = 2,
                titulo = "Introducción a Scrum",
                descripcion = "Estudio de los roles, eventos y artefactos principales de Scrum.",
                fecha = "14 de agosto",
                estado = "En proceso",
                progreso = 70
            ),
            onCompletar = {}
        )
    }
}


/* =========================================================
   PREVIEW 3
   Título largo
   ========================================================= */

@Preview(
    showBackground = true,
    name = "Título largo"
)
@Composable
fun TarjetaActividadTituloLargoPreview() {

    MiFormacionCTMATheme {

        TarjetaActividad(
            actividad = ActividadFormativa(
                id = 3,
                titulo = "Actividad de integración final de conocimientos sobre desarrollo de aplicaciones móviles con Kotlin y Jetpack Compose",
                descripcion = "Esta actividad permite comprobar que la interfaz se adapta correctamente cuando los textos son extensos.",
                fecha = "20 de agosto",
                estado = "En proceso",
                progreso = 50
            ),
            onCompletar = {}
        )
    }
}


/* =========================================================
   PREVIEW 4
   Texto aumentado a 1.5
   ========================================================= */

@Preview(
    showBackground = true,
    name = "Texto grande 1.5"
)
@Composable
fun TarjetaActividadTextoGrandePreview() {

    val densidad = LocalDensity.current

    MiFormacionCTMATheme {

        CompositionLocalProvider(
            LocalDensity provides Density(
                density = densidad.density,
                fontScale = 1.5f
            )
        ) {

            TarjetaActividad(
                actividad = ActividadFormativa(
                    id = 4,
                    titulo = "Accesibilidad",
                    descripcion = "Revisión del tamaño del texto, contraste, zonas táctiles y accesibilidad.",
                    fecha = "18 de agosto",
                    estado = "Pendiente",
                    progreso = 0
                ),
                onCompletar = {}
            )
        }
    }
}