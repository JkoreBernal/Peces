# Documentación técnica – Peces

**Proyecto:** Peces  
**Versión:** Actividad Formativa 3  
**Lenguaje:** Java 21  
**Interfaz gráfica:** Java Swing / Java2D  
**Arquitectura:** Programación orientada a objetos  
**Repositorio:** [JkoreBernal/Peces](https://github.com/JkoreBernal/Peces)

---

## 1. Descripción general

Peces es un videojuego 2D desarrollado en Java mediante Swing y Java2D. El proyecto implementa personajes interactivos, habilidades especiales, enemigos y elementos gráficos organizados mediante programación orientada a objetos.

En esta versión se realizó una refactorización de la estructura del código para implementar herencia, reutilización de componentes, sobrescritura de métodos y separación de responsabilidades mediante paquetes.

## 2. Arquitectura del proyecto

El código fuente se encuentra organizado en paquetes funcionales:

```text
src/
├── Main.java
├── peces/
│   ├── Pez.java
│   ├── Anguila.java
│   ├── Globo.java
│   └── Betta.java
├── habilidades/
│   ├── Electricidad.java
│   ├── Expansion.java
│   └── Rebote.java
├── enemigos/
│   └── Pulpo.java
└── niveles/
    ├── Nivel.java
    ├── Burbujas.java
    ├── Algas.java
    ├── Paredes.java
    ├── Escenario.java
    ├── Botones.java
    └── Camara.java
```

### Responsabilidades de los paquetes

| Paquete | Responsabilidad |
|---|---|
| `peces` | Modelos de personajes y comportamiento base. |
| `habilidades` | Implementación de habilidades especiales. |
| `enemigos` | Entidades enemigas e interacciones. |
| `niveles` | Administración del nivel y componentes del escenario. |

`Main.java` funciona como punto de entrada de la aplicación.

## 3. Modelo de herencia

Se implementó `Pez.java` como clase base abstracta para centralizar atributos y comportamientos compartidos por los personajes.

Las clases derivadas son:

- `Anguila`
- `Globo`
- `Betta`

La relación de herencia se establece mediante `extends`.

```java
public class Anguila extends Pez {
    // Implementación específica de la anguila
}
```

La clase base proporciona una estructura común, mientras que las clases derivadas especializan el comportamiento de cada personaje.

### Jerarquía de clases

```text
                 Pez
                  |
        ┌─────────┼─────────┐
        |         |         |
     Anguila    Globo     Betta
        |         |         |
  Electricidad Expansion  Rebote
```

## 4. Sobrescritura y polimorfismo

Las clases derivadas personalizan el método `dibujar` mediante sobrescritura (`@Override`).

Esto permite mantener una interfaz de comportamiento común y proporcionar diferentes implementaciones gráficas según el tipo de personaje.

El uso de una clase base y métodos sobrescritos facilita extender el sistema sin duplicar toda la lógica de los personajes.

## 5. Sistema de habilidades

Las habilidades se encuentran separadas de los modelos de personajes para mantener una mejor distribución de responsabilidades.

| Clase | Funcionalidad |
|---|---|
| `Electricidad.java` | Comportamiento eléctrico de la anguila. |
| `Expansion.java` | Habilidad de expansión del pez Globo. |
| `Rebote.java` | Comportamiento de rebote del Betta. |

Esta separación permite mantener las funcionalidades especiales en componentes independientes.

## 6. Sistema de enemigos

El paquete `enemigos` contiene la clase `Pulpo.java`.

Esta clase representa la entidad enemiga y participa en las interacciones del videojuego.

La clase `Nivel.java` utiliza la funcionalidad de colisión del pulpo para gestionar su interacción con los personajes.

## 7. Administración del nivel

El paquete `niveles` contiene las clases encargadas de organizar el entorno y el funcionamiento del videojuego.

| Clase | Función |
|---|---|
| `Nivel` | Control central del nivel y los personajes. |
| `Burbujas` | Elementos visuales de burbujas. |
| `Algas` | Elementos gráficos de algas. |
| `Paredes` | Elementos relacionados con las paredes. |
| `Escenario` | Organización del entorno visual. |
| `Botones` | Componentes gráficos de interacción. |
| `Camara` | Administración de la visualización del escenario. |

## 8. Instanciación de personajes

La clase `Nivel` crea instancias de las clases derivadas para incorporarlas al sistema:

```java
anguila = new Anguila(inicioX, inicioY);
globo = new Globo(inicioX, inicioY);
betta = new Betta(inicioX, inicioY);
```

Cada objeto mantiene las características heredadas de `Pez` y su comportamiento especializado.

## 9. Principios de programación orientada a objetos

La implementación utiliza los siguientes conceptos:

- **Abstracción:** definición de una clase base que representa las características generales de un pez.
- **Herencia:** reutilización de atributos y métodos de `Pez` en sus clases derivadas.
- **Encapsulamiento:** organización y control del acceso a los atributos mediante los mecanismos definidos en las clases.
- **Polimorfismo:** especialización del método `dibujar` en los diferentes personajes.
- **Modularidad:** separación de responsabilidades mediante paquetes.
- **Instanciación:** creación de objetos a partir de las clases concretas.

## 10. Compilación y ejecución

Requisitos:

- JDK 21 o compatible.
- Terminal con acceso al compilador `javac`.
- Entorno gráfico compatible con Java Swing.

Desde el directorio raíz del proyecto:

```bash
mkdir -p out
```

Compilar:

```bash
javac -d out src/Main.java src/niveles/*.java src/peces/*.java src/enemigos/*.java src/habilidades/*.java
```

Ejecutar:

```bash
java -cp out Main
```

Los archivos compilados se almacenan en `out/`, manteniendo separados los archivos fuente y el código ejecutable.

## 11. Control de versiones

El desarrollo se administra mediante Git y GitHub.

| Rama | Propósito |
|---|---|
| `main` | Rama principal del repositorio. |
| `actividad-formativa-2` | Conservación de la versión anterior. |
| `actividad-formativa-3` | Implementación de herencia y reorganización modular. |

Los cambios se registran mediante commits y se sincronizan con el repositorio remoto mediante `git push`.

## 12. Mejoras de esta versión

- Implementación de una clase base abstracta `Pez`.
- Incorporación de clases derivadas.
- Sobrescritura de métodos de dibujo.
- Separación de habilidades especiales.
- Organización del código en paquetes.
- Distribución de responsabilidades entre componentes.
- Conservación de la versión anterior mediante ramas Git.

---

**Autor:** José Samuel Bernal Hernández  
**Proyecto académico:** Ingeniería en Desarrollo de Software  
**Repositorio:** [GitHub – Peces](https://github.com/JkoreBernal/Peces)

