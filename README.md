# Biblioteca Virtual Académica

Este repositorio contiene el código fuente de la **Biblioteca Virtual Académica**, una plataforma web diseñada para centralizar, organizar y distribuir material académico de manera eficiente dentro de instituciones educativas. El sistema permite a los docentes administrar documentos digitales, guías y talleres, mientras que los estudiantes disponen de un acceso optimizado para la consulta y descarga de dichos recursos desde cualquier dispositivo con conexión a internet.

El desarrollo del proyecto se fundamenta en el framework **Spring Boot** bajo el patrón arquitectónico **Modelo-Vista-Controlador (MVC)**, garantizando una estructura escalable, organizada y segura.

---

## Integrantes del Grupo
* Harol Velasco
* Brandon Zafra

---

## 1. Objetivos del Proyecto

### Objetivo General
Desarrollar una biblioteca virtual académica utilizando Spring Boot que permita a los profesores administrar archivos digitales y a los estudiantes acceder a ellos para su lectura y consulta.

### Objetivos Específicos
* Diseñar una plataforma web con interfaces diferenciadas para profesores y estudiantes.
* Implementar un módulo de autenticación y control de acceso basado en roles.
* Desarrollar las funciones necesarias para que los profesores puedan subir, editar y eliminar archivos académicos.
* Permitir a los estudiantes la visualización, lectura y descarga de los documentos disponibles.
* Garantizar la persistencia y almacenamiento de la información mediante una base de datos relacional.
* Aplicar de forma estricta el patrón de arquitectura Modelo-Vista-Controlador (MVC).

---

## 2. Justificación
En el contexto educativo actual, las instituciones requieren de herramientas tecnológicas centralizadas que agilicen el flujo de material de estudio. Esta plataforma resuelve dicha necesidad al proveer un entorno seguro para la gestión documental por parte de los profesores y un acceso simplificado para el alumnado. Desde la perspectiva técnica, la implementación con Spring Boot permite aplicar arquitecturas backend modernas, buenas prácticas de desarrollo web, gestión avanzada de permisos y persistencia de datos relacionales.

---

## 3. Entorno de Trabajo y Tecnologías

El ecosistema tecnológico utilizado para el desarrollo del proyecto comprende las siguientes herramientas y lenguajes:

### Software y Herramientas
* **Framework Principal:** Spring Boot
* **Sistema de Gestión de Base de Datos:** MySQL
* **Servidor Local:** XAMPP
* **Control de Versiones:** Git

### Tecnologías y Lenguajes
* **Backend:** Java
* **Frontend (Capa de Vista):** Thymeleaf, HTML, CSS
* **Persistencia:** SQL

### Arquitectura del Sistema
La aplicación implementa el patrón **Modelo-Vista-Controlador (MVC)** para segmentar las responsabilidades del sistema:
* **Modelo:** Encargado de la gestión de entidades, reglas de negocio y comunicación con la base de datos.
* **Vista:** Interfaces gráficas de usuario desarrolladas mediante plantillas dinámicas con Thymeleaf.
* **Controlador:** Lógica de control que intercepta las peticiones HTTP, gestiona el flujo de la aplicación y conecta el Modelo con la Vista.

---

## 4. Requerimientos del Sistema

### Requerimientos Funcionales
* El sistema debe permitir el registro de nuevos usuarios.
* El sistema debe permitir el inicio de sesión mediante credenciales seguras.
* El sistema debe diferenciar las interfaces y permisos entre profesores y estudiantes.
* Los profesores podrán subir archivos en formato PDF y otros documentos académicos.
* Los profesores podrán actualizar o editar la metainformación de los archivos.
* Los profesores podrán eliminar archivos del sistema.
* Los estudiantes podrán visualizar el listado de archivos disponibles.
* Los estudiantes podrán leer o descargar los documentos académicos.
* El sistema debe almacenar de forma íntegra la información en la base de datos relacional.

### Requerimientos No Funcionales
* **Usabilidad:** La plataforma debe contar con una interfaz intuitiva y de fácil navegación.
* **Rendimiento:** El sistema debe ofrecer tiempos de respuesta rápidos ante las peticiones del usuario.
* **Seguridad:** La información almacenada debe estar protegida y el acceso restringido según el rol asignado.
* **Compatibilidad:** La interfaz debe ser completamente responsiva, adaptándose a computadores y dispositivos móviles.
