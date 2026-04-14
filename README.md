# 🚚 Transport API

API REST para la gestión de órdenes de transporte y conductores, desarrollada con **Java + Spring Boot** siguiendo buenas prácticas de arquitectura, seguridad y despliegue.

---

## 🧩 Tecnologías utilizadas

* Java 17
* Spring Boot 3
* Spring Data JPA (Hibernate)
* Spring Security + JWT
* MySQL
* Docker & Docker Compose
* Swagger / OpenAPI
* Lombok

---

## 📦 Funcionalidades

### 📌 Órdenes

* Crear orden
* Obtener orden por ID
* Listar órdenes con filtros
* Actualizar estado (con validación de flujo)
* Asignar conductor a una orden

### 🚗 Conductores

* Crear conductor
* Listar conductores activos

### 📎 Archivos

* Subida de archivos:

  * PDF
  * Imagen (JPG/PNG)

---

## 🔐 Seguridad

* Autenticación basada en **JWT**
* Endpoints protegidos con Spring Security
* Swagger habilitado sin autenticación para pruebas

---

## 📚 Documentación API

Disponible en:

http://localhost:8080/swagger-ui/index.html

---

## 🐳 Ejecución con Docker

### 1. Clonar repositorio

```bash
git clone https://github.com/tu-usuario/transport-api.git
cd transport-api
```

---

### 2. Construir el proyecto

```bash
mvn clean package -DskipTests
```

---

### 3. Ejecutar contenedores

```bash
docker-compose up --build
```

---

### 4. Acceder a la API

* API: http://localhost:8080
* Swagger: http://localhost:8080/swagger-ui/index.html

---

## ⚙️ Configuración

La aplicación usa variables de entorno:

```bash
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
```

Docker las configura automáticamente.

---

## 📂 Estructura del proyecto

```
com.transport.api
│
├── controller
├── service
├── repository
├── model
├── dto
├── mapper
├── security
├── exception
└── config
```

---

## 🧠 Decisiones técnicas

* Arquitectura en capas (Controller → Service → Repository)
* Uso de DTOs para desacoplar entidades
* Manejo centralizado de errores con @ControllerAdvice
* Validaciones con Jakarta Validation
* Manejo de archivos con servicio dedicado
* Configuración externalizada (12-factor app)
* Preparado para migración a microservicios

---

## 🧪 Pruebas

Se incluyen pruebas unitarias con:

* JUnit 5
* Mockito

---

## 🚀 Mejoras futuras

* Implementación de microservicios (Order / Driver / File)
* Integración con almacenamiento en la nube (S3)
* Uso de MapStruct
* CI/CD pipeline

---

## 👨‍💻 Autor
@luismotaiv
