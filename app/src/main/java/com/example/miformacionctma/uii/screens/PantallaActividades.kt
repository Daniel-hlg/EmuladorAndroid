@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
package com.example.miformacionctma.uii.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miformacionctma.model.ActividadFormativa
import com.example.miformacionctma.uii.components.TarjetaActividad
import com.example.miformacionctma.ui.theme.MiFormacionCTMATheme

@Composable
fun PantallaActividades(
    actividades: List<ActividadFormativa> = actividadesEjemplo
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Mi Formación CTMA")
                }
            )
        }
    ) { paddingValues ->

        if (actividades.isEmpty()) {

            EstadoVacio(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            text = "Actividades formativas",
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Text(
                            text = "Consulta tus actividades, fechas, estados y progreso.",
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                items(
                    items = actividades,
                    key = { actividad -> actividad.id }
                ) { actividad ->

                    TarjetaActividad(
                        actividad = actividad
                    )
                }
            }
        }
    }
}

@Composable
fun EstadoVacio(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "No hay actividades",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "Todavía no tienes actividades registradas.",
                modifier = Modifier.padding(top = 8.dp)
            )

            Button(
                onClick = {},
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Actualizar")
            }
        }
    }
}

val actividadesEjemplo = listOf(

    ActividadFormativa(
        id = 1,
        titulo = "Introducción al desarrollo móvil",
        descripcion = "Conceptos básicos del desarrollo de aplicaciones móviles Android.",
        fecha = "11 de agosto",
        estado = "Completada",
        progreso = 100
    ),

    ActividadFormativa(
        id = 2,
        titulo = "Programación en Kotlin",
        descripcion = "Variables, funciones, colecciones y clases en Kotlin.",
        fecha = "12 de agosto",
        estado = "Completada",
        progreso = 100
    ),

    ActividadFormativa(
        id = 3,
        titulo = "Manifiesto Ágil",
        descripcion = "Estudio de los cuatro valores y doce principios del Manifiesto Ágil.",
        fecha = "13 de agosto",
        estado = "Completada",
        progreso = 100
    ),

    ActividadFormativa(
        id = 4,
        titulo = "Introducción a Scrum",
        descripcion = "Roles, eventos y artefactos principales de Scrum.",
        fecha = "14 de agosto",
        estado = "En proceso",
        progreso = 70
    ),

    ActividadFormativa(
        id = 5,
        titulo = "Pruebas de software",
        descripcion = "Identificación de los principales tipos de pruebas de software.",
        fecha = "15 de agosto",
        estado = "En proceso",
        progreso = 60
    ),

    ActividadFormativa(
        id = 6,
        titulo = "Jetpack Compose",
        descripcion = "Construcción de interfaces utilizando Jetpack Compose.",
        fecha = "16 de agosto",
        estado = "En proceso",
        progreso = 50
    ),

    ActividadFormativa(
        id = 7,
        titulo = "Material 3",
        descripcion = "Uso de componentes, colores y tipografía de Material 3.",
        fecha = "17 de agosto",
        estado = "Pendiente",
        progreso = 0
    ),

    ActividadFormativa(
        id = 8,
        titulo = "Accesibilidad",
        descripcion = "Revisión de contraste, tamaño de texto y zonas táctiles.",
        fecha = "18 de agosto",
        estado = "Pendiente",
        progreso = 0
    ),

    ActividadFormativa(
        id = 9,
        titulo = "Diseño adaptable",
        descripcion = "Adaptación de la interfaz para diferentes tamaños de pantalla.",
        fecha = "19 de agosto",
        estado = "Pendiente",
        progreso = 0
    ),

    ActividadFormativa(
        id = 10,
        titulo = "Proyecto Mi Formación CTMA",
        descripcion = "Integración de las actividades y evidencias del proceso formativo.",
        fecha = "20 de agosto",
        estado = "Pendiente",
        progreso = 0
    )
)

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PantallaActividadesPreview() {
    MiFormacionCTMATheme {
        PantallaActividades()
    }
}

@Preview(showBackground = true)
@Composable
fun EstadoVacioPreview() {
    MiFormacionCTMATheme {
        EstadoVacio(
            modifier = Modifier.fillMaxSize()
        )
    }
}