# Centro Nicaragüense de Formación Tecnológica — Sistema de Matrícula (`Centro_Nic`)

Aplicación de escritorio desarrollada en **JavaFX 21** y **Scene Builder** para la gestión de matrícula de estudiantes del **Centro Nicaragüense de Formación Tecnológica**.
---

## Integrantes del Equipo

* **Donald Hernández** — *Desarrollador / Integrante*
* **Claudio De La Rocha** — *Desarrollador / Integrante*

---

## Descripción del Proyecto

El proyecto responde al **Caso 1: Sistema de matrícula** de la Evaluación Sistemática de JavaFX Temurin 21. Permite registrar, mostrar, actualizar, filtrar y eliminar la información de los estudiantes matriculados en cursos como *Programación*, *Excel*, *Redes* y *Diseño Gráfico*.

### Características Principales:
* **Operaciones CRUD Completas:** Registro, actualización, selección mediante eventos y eliminación de registros.
* **Manejo de Memoria Temporal:** Uso de `ObservableList<Estudiante>` vinculado dinámicamente con `TableView`.
* **Diseño Visual FXML:** Interfaz construida con Scene Builder mediante contenedores responsivos (`VBox`, `SplitPane`, `GridPane`, `HBox`).
* **Validaciones Robustas:** Notificación de errores de entrada en tiempo real mediante diálogos `Alert` (Campos vacíos, longitud de usuario $\ge 5$, contraseña $\ge 8$, selección de curso, modalidad, horario y aceptación de normas).
* **Menús Interactivos**
  * **MenuBar:** Menús interactivos de *Archivo* (Nuevo, Salir) y *Ayuda* (Acerca de).
  * **ToolBar:** Acceso rápido para *Guardar*, *Limpiar* y *Eliminar*.
  * **ContextMenu:** Menú contextual emergente (clic derecho sobre la tabla) con opciones *Editar* y *Eliminar*.
* **Control de Eventos Avanzado:**
  * `ActionEvent`: Manejo de botones y elementos del menú.
  * `MouseEvent`: Carga automática del registro al hacer **doble clic** sobre una fila del `TableView`.
  * `KeyEvent`: Ejecución con teclas rápidas (`ENTER` para guardar, `ESCAPE` para limpiar).

---

## Requisitos de Software y Herramientas

* **JDK:** Java SE Development Kit 21 (Eclipse Temurin / OpenJDK 21).
* **JavaFX SDK:** Versión 21.
* **IDE:** IntelliJ IDEA 2023+ (Community o Ultimate).
* **Diseñador de Interfaz:** Scene Builder 21+.
* **Gestor de Control de Versiones:** Git & GitHub.

