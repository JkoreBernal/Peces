# Peces – Videojuego 2D en Java

## Descripción
Peces es una aplicación sencilla desarrollada en Java que representa un videojuego 2D con temática submarina. El proyecto permite seleccionar y lanzar tres personajes: Rayo, Globo y Saltín, con el objetivo de impactar a un pulpo inmóvil.

## Tecnologías utilizadas
- Java 21
- Swing
- Java2D
- Visual Studio Code

## Estructura del proyecto

| Archivo | Descripción |
|---|---|
| Main.java | Inicia la aplicación y crea la ventana principal. |
| Pez.java | Define los atributos, constructor y métodos de los personajes. |
| Pulpo.java | Define al enemigo y su representación gráfica. |
| Nivel.java | Crea los objetos y controla el escenario del videojuego. |

## Conceptos de programación aplicados

- Clases: definición de Pez, Pulpo y Nivel.
- Atributos: características de los personajes y enemigos.
- Constructores: inicialización de los objetos.
- Instanciación: creación de los tres peces y el pulpo mediante `new`.
- Métodos: dibujo, movimiento y lanzamiento de los personajes.
- Comentarios: documentación de las secciones importantes del código.

## Ejecución

Desde la terminal, dentro de la carpeta del proyecto:

```bash
mkdir -p out
javac -d out src/*.java
java -cp out Main
```

## Autor
José Samuel Bernal Hernández

## Proyecto académico
Actividad formativa 2 – Desarrollo de una Aplicación Simple en Línea.