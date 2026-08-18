package com.example.miformacionctma.uii.components

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

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

import com.example.miformacionctma.model.ActividadFormativa


@Composable
fun TarjetaActividad(
    actividad: ActividadFormativa,
    onCompletar: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                contentDescription =
                    "Actividad ${actividad.titulo}, " +
                            "estado ${actividad.estado}, " +
                            "progreso ${actividad.progreso} por ciento"
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


            // BOTÓN PARA COMPLETAR

            Button(
                onClick = onCompletar,
                enabled = actividad.estado != "Completada",
                modifier = Modifier.fillMaxWidth()
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