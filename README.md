![](https://github.com/sabrinaceciliajeria-cmyk/RefugioAnimales/raw/main/screenshot-1775781823.png)

```markdown
# 🎬 CineApp

Sistema de gestión de cine por consola desarrollado en Java,
aplicando principios de Programación Orientada a Objetos (POO).

## 📋 Descripción

CineApp permite gestionar clientes, películas, funciones y compra
de entradas a través de un menú interactivo por consola.

## 🛠️ Tecnologías

- Java 21+
- Paradigma: Programación Orientada a Objetos
- Sin dependencias externas

## 📁 Estructura del proyecto

CineApp/
├── src/
│   ├── app/
│   │   └── Main.java
│   ├── model/
│   │   ├── Persona.java
│   │   ├── Cliente.java
│   │   ├── Pelicula.java
│   │   ├── Funcion.java
│   │   └── Entrada.java
│   ├── service/
│   │   └── CineService.java
│   └── util/
│       └── ConsolaUtil.java
└── README.md

## ▶️ Cómo ejecutar

1. Clonar el repositorio
   git clone https://github.com/tu-usuario/CineApp.git

2. Compilar el proyecto
   javac -d out src/**/*.java

3. Ejecutar
   java -cp out app.Main

## 🧩 Funcionalidades

- Crear y gestionar clientes
- Registrar películas y funciones
- Comprar entradas con validación de disponibilidad
- Consultar entradas compradas por cliente
- Manejo de errores con try/catch

## 👥 Autores

 Arantxa Fischer
 Manuel Labrador
 Cristian Díaz
 Cristopher Contreras
 Natalia Medel
 Diego Peña
 Sabrina Jeria
```

---

## 👥 División de tareas (7 personas)

La clave es que **cada persona tenga una responsabilidad clara y sin bloqueos** entre sí al inicio. Por eso se recomienda definir primero una estructura base.

---

### ⚠️ Paso previo (hacer entre todos o asignarlo al líder del equipo)

Antes de dividir, alguien debe crear el repositorio y la estructura de carpetas vacía con los archivos `.java` en blanco, para que todos puedan trabajar en paralelo sin conflictos.

```
CineApp/
└── src/
    ├── app/Main.java         
    ├── model/Persona.java     
    ├── model/Cliente.java   
    ├── model/Pelicula.java   
    ├── model/Funcion.java    
    ├── model/Entrada.java    
    ├── service/CineService.java 
    └── util/ConsolaUtil.java 
```

Esto evita que nadie espere a nadie para comenzar.

---



### ✅ Convenciones de equipo

- Usar **Git con ramas por persona** (`feature/persona`, `feature/funcion`, etc.)
- Acordar **nombres de atributos y métodos** antes de comenzar para evitar conflictos
