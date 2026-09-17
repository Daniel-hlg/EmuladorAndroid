## 📱 Mi Formación CTMA
Aplicación móvil desarrollada en Android Studio con Kotlin y Jetpack Compose, creada para consultar y llevar el seguimiento de las actividades formativas.

---

## 🎯 Objetivo
Permitir visualizar de forma sencilla las actividades de formación, mostrando su descripción, fecha, estado y porcentaje de progreso.

---

## ✨ Funcionalidades
📋 Visualización de actividades formativas.
📅 Fecha de cada actividad.
🔄 Estado: completada, en proceso o pendiente.
📊 Barra de progreso.
📂 Tarjetas desplegables para mostrar detalles adicionales.
📱 Interfaz adaptable a diferentes tamaños de pantalla.

---

## 🛠️ Tecnologías
Kotlin
Android Studio
Jetpack Compose
Git
GitHub

---

## 📁 Estructura principal
app/
└── src/main/java/com/example/miformacionctma/
├── model/
│   └── ActividadFormativa.kt
├── ui/
│   ├── components/
│   │   └── TarjetaActividad.kt
│   ├── screens/
│   │   └── PantallaActividades.kt
│   └── theme/
└── MainActivity.kt

---

## 📊 Actividades
La aplicación contiene actividades relacionadas con temas como:

Manifiesto Ágil
Scrum
Pruebas de software
Jetpack Compose
Proyecto Mi Formación CTMA
Cada actividad muestra su respectivo progreso y estado.

---

## 🧪 Pruebas
La aplicación fue probada en Android Emulator – Pixel 7 API 34, verificando la visualización de las actividades y el funcionamiento de las tarjetas desplegables.

---

## 🔀 Control de versiones
El proyecto utiliza Git y GitHub para registrar y compartir los cambios realizados durante el desarrollo.

---

## ‍💻 Autor
Daniel Steven Holguin Soto

Proyecto académico desarrollado para el programa Análisis y Desarrollo de Software (ADSO) – SENA CTMA.

---

## 📌 Estado
🟢 En desarrollo.

---

## Semana 1 -- preguntas de comprension
¿Qué diferencia práctica encuentras entre una aplicación móvil y una página web? Respuesta: Una aplicación móvil se instala en el celular y está diseñada especialmente para usarse desde ahí, mientras que una página web se abre desde un navegador.

¿Qué función cumple un sistema operativo como Android? Respuesta: Es el sistema que permite que el celular funcione y que las aplicaciones puedan ejecutarse y utilizar los recursos del dispositivo.

¿Qué es una variable? Escribe un ejemplo relacionado con una actividad formativa. Respuesta: Una variable es un espacio donde podemos guardar un dato que puede cambiar. Por ejemplo, una variable llamada progreso puede guardar el porcentaje de avance de una actividad.

¿Qué estructura usarías para decidir si una actividad está vencida? Respuesta: Usaría una estructura if, porque permite comparar la fecha de la actividad con la fecha actual y decidir si ya está vencida.

¿Qué resultado esperas de una lista que almacena actividades? Respuesta: Espero que me permita guardar varias actividades y poder mostrarlas, consultarlas y recorrerlas fácilmente.

¿Para qué sirve un sistema de control de versiones? Respuesta: Sirve para guardar los cambios que se hacen en un proyecto y poder saber qué se modificó. También permite volver a una versión anterior si es necesario.

¿Qué información nunca debería subirse a un repositorio público? Respuesta: No debería subir contraseñas, claves, datos personales, tokens ni información privada de los usuarios.

¿Qué harías primero si una aplicación se cierra inesperadamente? Respuesta: Primero revisaría qué estaba haciendo cuando se cerró y buscaría el error en los mensajes o registros de la aplicación para saber qué lo causó.

Explica con tus palabras qué significa “probar” una aplicación. Respuesta: Probar una aplicación significa usarla y revisar que sus funciones trabajen correctamente y que no tenga errores que afecten al usuario.

Identifica dos riesgos de privacidad en una app que almacena datos de aprendices. Respuesta: Un riesgo sería que alguien pueda acceder a los datos sin autorización. Otro sería que la información personal de los aprendices se filtre o se comparta sin permiso.

## Semana 2 - preguntas para validar aprendizaje
¿Por qué elegiste val o var en un dato específico? Respuesta: Usé val cuando el dato no necesitaba cambiar, por ejemplo el título o la fecha de una actividad. Usé var cuando el dato podía cambiar durante el funcionamiento de la aplicación, como el estado de una tarjeta desplegable.

¿Qué pasaría si la lista estuviera vacía? Respuesta: No se mostrarían actividades. Lo ideal es controlar ese caso y mostrar un mensaje diciendo que no hay actividades registradas, en vez de dejar la pantalla vacía.

¿Dónde podría aparecer null y cómo lo controlaste? Respuesta: null podría aparecer cuando un dato no tenga ningún valor. Lo controlaría comprobando primero si el dato existe antes de utilizarlo, para evitar que la aplicación se cierre por un error.

¿Por qué una regla no debería estar duplicada dentro del Composable? Respuesta: Porque si la misma regla está repetida, el código se vuelve más difícil de mantener y modificar. Es mejor tenerla en un solo lugar para que sea más fácil cambiarla cuando sea necesario.

Modifica el criterio de urgencia de dos a tres días y demuestra el resultado. Respuesta: Cambiaría la condición que considera una actividad como urgente para que tenga en cuenta las actividades cuya fecha de entrega esté a tres días o menos. De esta manera, una actividad que antes no aparecía como urgente por estar a tres días, ahora sí sería marcada como urgente.

## Semana 3 - Incremento en Mi Formación CTMA
PantallaActividades.kt
Se construyó un Scaffold con TopAppBar para el título principal.
Se implementó un estado vacío con mensaje y botón de acción.
Se agregó un LazyColumn con encabezado y lista de actividades usando clave estable (id).
Se integró el componente TarjetaActividad para mostrar cada actividad.
Se definió una lista de 10 actividades de ejemplo con diferentes estados y progreso.
Se aplicó MaterialTheme para tipografía y estilos.
Se crearon Previews para la pantalla principal y el estado vacío.
Adaptación y Previews adicionales
Se implementó ContenidoAdaptable con BoxWithConstraints para cambiar entre LazyColumn y LazyVerticalGrid según el ancho disponible.
Se añadieron dos previews extra:
Fuente grande (fontScale = 1.5f).
Ancho ampliado (widthDp = 700).
Esto permite validar accesibilidad y diseño adaptable en diferentes configuraciones.
Pruebas y Scrum

## 📋 Checklist UX / Accesibilidad
Criterio	Evidencia en el proyecto
Contraste	Tipografía y colores Material 3 garantizan contraste.
Orden de lectura	Jerarquía clara: título → subtítulo → lista.
Escalado de fuente	Preview con fontScale = 1.5f muestra adaptación.
Zonas táctiles	Botón “Actualizar” y tarjetas con padding ≥48dp.
Diseño adaptable	BoxWithConstraints alterna lista y grid según ancho.
Estado vacío	Mensaje y acción clara cuando no hay actividades.
Evidencia visual	Capturas: lista, estado vacío, fuente grande, grid.

---

## Semana 4 - Estado, Formularios y Navegación con Jetpack Compose

En esta semana se transformaron los componentes estáticos de la interfaz en un incremento interactivo y multipantalla mediante la gestión de estado y el patrón de Flujo Unidireccional de Datos (UDF):

* **Flujo Unidireccional de Datos (UDF):** Implementación del patrón de elevación de estado (*State Hoisting*), separando la interfaz en composables *stateless* que reciben el estado y emiten eventos hacia su contenedor propietario.
* **Persistencia Temporal de la UI:** Conservación de borradores y restauración del estado ante eventos del ciclo de vida (como la rotación del dispositivo) utilizando `rememberSaveable`.
* **Formulario y Validaciones:** Construcción de `FormularioActividad` con validación de entradas en tiempo real (título obligatorio de 3 a 80 caracteres, descripción opcional de máximo 240 caracteres y progreso entre 0 y 100%), incluyendo `isError` y `supportingText` accesibles para la comunicación de errores.
* **Navegación Multipantalla (Navigation Compose):** Configuración del `NavHost` para conectar las rutas de *Listado*, *Creación* y *Detalle*, pasando únicamente identificadores de dominio (`actividadId`) a través de la pila de navegación (*back stack*) para garantizar un manejo seguro de la memoria.
* **Protección de Eventos:** Control de navegación y guardado para evitar duplicaciones por múltiples pulsaciones continuas (*doble toque*).

---

## Semana 6 - Gestión del Marco de Trabajo Scrum

En esta semana se reestructuró el proyecto dentro del marco de trabajo Scrum, estableciendo los artefactos iniciales y los acuerdos de calidad:

* **Product Goal:** Consolidar la plataforma "Mi Formación CTMA" para la gestión y seguimiento reactivo de actividades formativas y reportes del aprendiz.
* **Sprint Goal (Sprint 1):** Construir el flujo principal de registro y visualización de reportes de actividades con persistencia de datos local.
* **Definition of Done (DoD) Inicial:** 
  * Criterios de aceptación de las Historias de Usuario (HU1, HU2, HU3) verificados.
  * Código integrado a la rama principal mediante Pull Request revisado por el equipo.
  * Interfaz de usuario navegable y funcional sin fallos de ejecución.
* **Métricas y Estimación:** Refinamiento del backlog, estimación de Historias de Usuario en puntos de historia y aplicación del enfoque *shift-left* para planificar la calidad desde las fases iniciales.

---

## Semana 7 - Arquitectura Reactiva y Persistencia con Room

En esta semana se realizó la migración del proyecto a una arquitectura reactiva y concurrente basada en MVVM, Room y Corrutinas:

* **Flujos Reactivos (Flow & StateFlow):** Integración de Room con `Flow` y `StateFlow` para emitir cambios de la base de datos a la interfaz en tiempo real.
* **Manejo de Estado de UI (UiState):** Implementación de `ListadoUiState` (Cargando, Contenido, Vacío, Error) y `OperacionUiState` para retroalimentación visual inmediata.
* **Búsqueda Reactiva en Tiempo Real:** Filtro de catálogo de actividades mediante el operador `combine` en `CrearReporteViewModel`.
* **Operaciones Main-Safe:** Ejecución de inserciones, actualizaciones y consultas en hilos secundarios utilizando `viewModelScope` y Corrutinas.
* **Integración en Jetpack Compose:** Consumo de flujos en la interfaz utilizando `collectAsStateWithLifecycle()` y actualización del cálculo de progreso (0% a 100%) al completar actividades.

---

## Semana 8 - Selección de Candidatos a Automatizar

## Servicios Web REST, Caché Local y Resiliencia

En esta semana se integró la aplicación con servicios web RESTful mediante Retrofit, manteniendo a Room como la fuente única de verdad bajo la arquitectura *Offline-First*:

* **Capa Remota y DTOs:** Configuración de `ActividadApiService` y `RemoteActividadDataSource` con `kotlinx.serialization` y DTOs (`ActividadDto`) aislados de la capa de presentación.
* **Política Offline-First:** Método `refresh()` en `RoomReporteRepository`. La API actualiza Room atómicamente y, ante fallos de red o timeouts, Room conserva intacta la información en caché.
* **Resiliencia de Red:** OkHttp configurado con timeouts de 15s. Clasificación de excepciones con reintento y propagación explícita de `CancellationException`.
* **Respuestas al Cuestionario Teórico (8 Puntos):**
  1. **Códigos HTTP:** 200 (Éxito), 201 (Creado), 401 (No autorizado/Sesión caducada), 404 (No encontrado), 500 (Error de servidor).
  2. **Aislamiento de DTO:** `ActividadDto` no es modelo de UI para evitar que cambios en el contrato del backend rompan la presentación.
  3. **Flujo Offline-First:** API → DTO → Mapper → Room (Entity) → Repository → StateFlow → UI.
  4. **Timeout en Caché:** Si ocurre un timeout, la base de datos local conserva sus registros y la UI notifica el error de actualización sin perder datos.
  5. **Conectividad:** La verificación de red es una señal puntual, no una garantía permanente, ya que la conexión puede caer durante la petición.
  6. **Riesgo de Tokens:** Escribir tokens reales en código o Git provoca fugas de seguridad, suplantación y compromisos en el backend.
  7. **Capa para Authorization:** Debe configurarse en un `Interceptor` de OkHttp para inyectarlo de forma centralizada sin exponerlo en la UI.
  8. **CancellationException:** Se relanza explícitamente (`throw e`) para no interrumpir el flujo estructurado de corrutinas en Kotlin.

# A continuación se presenta la matriz de selección de casos de prueba de las semanas 3 y 4 priorizados para automatización:

| Caso / Regla | Riesgo | Frecuencia | Determinista | Nivel sugerido | ¿Automatizar? | Justificación | Referencia |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Mapeo de estado y progreso (0% vs 100%)** | Alto | Alta | Sí | Unidad | **Sí** | Es una regla pura del ViewModel; se ejecuta en cada carga. | FormularioActividadUiState |
| **Búsqueda reactiva por texto** | Medio | Alta | Sí | Unidad | **Sí** | Valida el operador `combine` del filtro de actividades. | CrearReporteViewModel |
| **Marcar actividad como completada** | Alto | Alta | Sí | Unidad / Integración | **Sí** | Cambia el estado persistido en Room al presionar la tarjeta. | `completarActividad()` |
| **Inserción de reporte en base de datos** | Crítico | Alta | Sí | Integración (Room) | **Sí** | Verifica que el DAO persista datos sin fallos. | `ReporteDaoTest` |
| **Validación de campos vacíos al guardar** | Medio | Alta | Sí | Unidad | **Sí** | Evita guardar registros corruptos o incompletos. | `guardarReporte()` |
| **Manejo de estado de error (UiState.Error)** | Medio | Ocasional | Sí | Unidad | **Sí** | Garantiza que la UI reciba la excepción correctamente. | `ListadoUiState.Error` |
| **Prueba exploratoria de diseño / UI** | Bajo | Baja | No | UI / Manual | **No** | Cambios de estilo visual; mejor evaluado manualmente. | UX / Pantalla |

---

## Definition of Done (DoD) 

* [x] **Casos de prueba seleccionados:** Matriz de automatización priorizada por riesgo y costo.
* [x] **Pruebas Unitarias (JUnit):** Lógica del ViewModel y `StateFlow` validados en verde.
* [x] **Pruebas de Integración (Room):** Operaciones CRUD sobre base de datos en memoria ejecutadas correctamente.
* [x] **Microciclo TDD:** Funcionalidad de eliminación `eliminarPorId` desarrollada bajo el ciclo Red -> Green -> Refactor.

## 🧪 Pruebas y Validación (Semana 8)

Se implementó y ejecutó una suite de pruebas para garantizar el funcionamiento del patrón Repository y la interfaz gráfica de usuario.

# 1. Pruebas Unitarias (`test`)
Validan la lógica de negocio y el manejo de datos en la capa de repositorio sin necesidad de levantar un emulador, utilizando el patrón **Fake**:

* **`ActividadRepositoryTest`**: Confirma la adición y actualización de estados dentro de la fuente de datos mediante `FakeReporteRepository` y la emisión correcta de eventos a través de `StateFlow`.

# 2. Pruebas Instrumentadas (`androidTest`)
Ejecutadas en entorno con **Android 13 (API 33)** para validar la representación de componentes con Jetpack Compose:

* **`PantallaActividadesTest`**: Verifica la renderización correcta de la cabecera principal, el catálogo de actividades y la presencia del campo de búsqueda/filtro.

### 🛠️ Configuración del Entorno de Pruebas
* **Target API:** Android 13 (API 33)
* **Frameworks:** JUnit 4, Kotlin Coroutines Test, Compose Test Framework

## Semana #9