package com.example.miformacionctma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.miformacionctma.ui.theme.MiFormacionCTMATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiFormacionCTMATheme {
                PantallaInicio(nombre = "Daniel")
            }
        }
    }
}

@Composable
fun PantallaInicio(nombre: String = "Daniel") {
    // Estado de scroll
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // habilita desplazamiento vertical
            .padding(24.dp)
    ) {
        Text(
            text = "Mi Formación CTMA",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Hola, $nombre")
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Aquí organizarás actividades y evidencias.\n")

        Text(
            text = """
                    El Manifiesto Ágil: es un acuerdo que hicieron unos programadores para trabajar mejor en equipo. 
                    Básicamente dice: “lo importante no es seguir reglas rígidas, sino trabajar juntos, adaptarnos rápido y entregar cosas útiles a las personas”.

                    🧩 Los 4 valores
                    Personas y conversaciones  
                    Es mejor hablar con la gente y entendernos, que solo escribir papeles largos. 
                    Como cuando prefieres hablar con tu amigo en vez de mandarle una carta gigante.

                    Software funcionando  
                    Es más valioso mostrar algo que ya sirve, aunque sea pequeño, que tener solo planes bonitos en papel. 
                    Como cuando haces un dibujo y lo enseñas, aunque no esté perfecto.

                    Colaboración con el cliente  
                    Es mejor trabajar junto con la persona que necesita lo que haces, que pelear por contratos o reglas. 
                    Como cuando tu mamá te dice cómo quiere el cuarto ordenado y tú lo haces con ella.

                    Responder al cambio  
                    Es mejor adaptarse si algo cambia, que seguir un plan viejo que ya no sirve. 
                    Como cuando ibas a jugar fútbol, pero llueve, entonces juegas escondidas.

                    🎈 Los 12 principios explicados como niño
                    Satisfacer al cliente  
                    Lo importante es hacer feliz a la persona que usa lo que hacemos.

                    Aceptar cambios  
                    Si alguien pide algo nuevo, no enojarse, sino intentar hacerlo.

                    Entregar seguido  
                    Mejor dar cosas pequeñas rápido, que esperar mucho tiempo.

                    Trabajar juntos  
                    Que los que hacen el trabajo y los que lo piden estén siempre hablando.

                    Motivar a las personas  
                    Si la gente está feliz y con confianza, trabaja mejor.

                    Conversaciones cara a cara  
                    Hablar directamente es más claro que mandar mensajes largos.

                    Software funcionando  
                    Lo que vale es que las cosas sirvan, no que estén solo planeadas.

                    Ritmo constante  
                    Es mejor trabajar a un paso tranquilo y constante, no correr y cansarse.

                    Atención a la calidad  
                    Hacer las cosas bien desde el inicio, no a la carrera.

                    Simplicidad  
                    Lo más fácil y simple suele ser lo mejor.

                    Equipos autoorganizados  
                    Dejar que el grupo decida cómo trabajar, porque juntos saben más.

                    Reflexionar y mejorar  
                    Cada cierto tiempo mirar lo que hicimos y pensar cómo hacerlo mejor.
                    
                    Scrum es un marco de trabajo ágil que sirve para organizar proyectos en ciclos cortos llamados sprints. La idea es que el equipo pueda entregar resultados útiles rápidamente y adaptarse a los cambios sin depender de planes rígidos. Dentro de Scrum existen tres roles principales. El Product Owner es la persona que se encarga de decidir qué se debe construir primero y de priorizar las necesidades del producto, siempre pensando en dar el mayor valor posible. El Scrum Master es quien ayuda al equipo a aplicar Scrum correctamente, elimina obstáculos y se asegura de que todos trabajen de manera fluida, pero no es un jefe, sino más bien un guía. El Equipo de Desarrollo es el grupo de personas que realmente construye el producto, trabajando de forma autoorganizada y multifuncional.

                    Scrum también tiene artefactos que ayudan a dar transparencia y orden. El Product Backlog es como una lista de todo lo que se quiere lograr en el producto, organizada por prioridades. El Sprint Backlog es la parte de esa lista que el equipo decide trabajar en un sprint específico. El Incremento es el resultado del sprint, es decir, la parte del producto que ya funciona y que se puede mostrar al cliente.

                    Además, Scrum se apoya en ceremonias que marcan el ritmo del trabajo. El Sprint Planning es la reunión donde se decide qué se hará en el sprint y cómo se logrará. El Daily Scrum es una reunión corta de quince minutos que se hace cada día para que el equipo se sincronice y vea cómo va el trabajo. El Sprint Review ocurre al final del sprint, cuando se muestra lo que se construyó y se recibe retroalimentación del cliente. El Sprint Retrospective también se hace al final, pero es una reunión interna del equipo para reflexionar sobre cómo trabajaron y qué pueden mejorar en el próximo sprint. Finalmente, el Sprint en sí es el ciclo de trabajo que dura entre una y cuatro semanas y que contiene todas estas ceremonias.

                    En palabras simples, Scrum es como un juego en equipo: cada persona tiene un rol claro, se usan listas para organizar lo que falta y lo que se está haciendo, y hay reuniones cortas para planear, revisar y mejorar constantemente.
            """.trimIndent()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaInicioPreview() {
    MiFormacionCTMATheme {
        PantallaInicio("Aprendiz")
    }
}