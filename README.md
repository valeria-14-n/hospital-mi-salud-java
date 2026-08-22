# Hospital "Su Salud" — Sistema de Gestión Hospitalaria

Proyecto final del curso **SC-304 Estructuras de Datos** — Universidad Fidélitas.

MVP de un sistema de administración hospitalaria desarrollado en Java, donde **todas las estructuras de datos están implementadas desde cero**. No se usa ninguna colección de la librería estándar de Java (`ArrayList`, `HashMap`, `Stack`, `Queue`, etc.), ni arreglos, ni genéricos. Esa fue la restricción principal del curso y es el punto central del proyecto: entender cómo funcionan las estructuras por dentro, no solo usarlas.

---

## Estructuras de datos implementadas

| Estructura | Clase | Dónde se usa |
|---|---|---|
| Cola | `Cola` / `ColaPacientes` | Fila de atención en Emergencias (FIFO) |
| Pila | `Pila` | Módulo de atención de quejas (LIFO) |
| Lista simple | `ListaSimple` / `ListaConteos` | Conteos y totalizaciones del módulo BI |
| Lista circular | `ListaCircular` | Histórico de citas y de medicamentos de cada paciente |
| Árbol binario de búsqueda | `ArbolBinario` / `ArbolExpedientes` | Expediente Único de Pacientes, indexado por cédula |

Todas las clases especializadas **heredan** de la estructura base en lugar de modificarla. Por ejemplo, `ArbolExpedientes extends ArbolBinario` y `NodoExpediente extends Nodo`: el nodo del expediente sigue siendo un nodo de árbol, así que el árbol lo acepta por polimorfismo, y la cédula del paciente viaja en el atributo `dato` de la clase padre.

---

## Módulos del sistema

### 1. Llegada de pacientes en Emergencias
Manejo de la fila de atención con una cola: asignación de fichas, atención por orden de llegada, abandono de la fila y consulta de fichas pendientes.

### 2. Atención de quejas
Registro y despacho de quejas usando una pila.

### 3. Expediente del paciente y bitácora de citas
Cada paciente guarda su histórico de citas y de medicamentos prescritos en listas circulares propias, almacenadas por composición dentro del nodo del expediente.

### 4. Expediente Único optimizado (ABB)
El expediente pasó de una lista enlazada (avance 2) a un **árbol binario de búsqueda** indexado por cédula, lo que reduce el costo de búsqueda de O(n) a O(log n) en un árbol balanceado.

Incluye la funcionalidad **"Cargar Expediente desde Archivo"**: lectura de un archivo JSON con pacientes y construcción del ABB a partir de él. El parseo del JSON está hecho **a mano**, usando únicamente `BufferedReader`, `FileReader` y métodos de `String` — sin librerías externas.

### 5. Módulo de Inteligencia Empresarial (BI)
Consultas para tomadores de decisión, todas resueltas recorriendo el árbol:

- **Enfermedades más frecuentes** — cuántas veces aparece cada diagnóstico.
- **Segmentación de pacientes** — totales de menores de edad, adultos y adultos mayores.
- **Detección de patrones** — consulta avanzada donde el usuario combina de 1 a 4 filtros: rango de edad, diagnóstico, género y medicamento.
- **Propuesta de valor** — *Top de medicamentos más recetados*, para apoyar decisiones de inventario y abastecimiento de farmacia.

---

## Tecnologías

- **Java** (JDK 8+)
- **Apache NetBeans**
- Aplicación de consola
- Imports permitidos en todo el proyecto: `java.util.Date`, `java.util.Scanner`, `java.io.BufferedReader`, `java.io.FileReader`

---

## Cómo ejecutarlo

1. Clonar el repositorio.
2. Abrir la carpeta del proyecto desde NetBeans (`File > Open Project`).
3. Ejecutar la clase principal (`Run Project` o F6).
4. Navegar el menú principal desde la consola.

Para probar la carga masiva de expedientes, usar el archivo JSON de ejemplo incluido en el repositorio.

---

## Nota sobre los datos

Los datos de pacientes del archivo JSON son **ficticios**, generados únicamente con fines académicos. No corresponden a personas reales ni a información médica real.

---

## Autores

Proyecto grupal (Grupo 4) — SC-304 Estructuras de Datos, Universidad Fidélitas.

- Valeria Barboza Chaves
- Kenneth Fabián Amador Fernández
- Kyle Sebastián Guzmán Martínez
- Brandon Mauricio Soto Salas

Profesor: José Alfredo Chaves Barboza
