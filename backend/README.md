# Backend - API REST de Gestión de Camiones

API REST desarrollada con Spring Boot y base de datos H2 para gestión de camiones empresariales.

## 🛠️ Tecnologías

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database
- JWT (JSON Web Tokens)
- Maven

## 🚀 Ejecución

```bash
# Compilar
mvn clean install

# Ejecutar
mvn spring-boot:run
```

## 📊 Base de Datos

La aplicación usa H2 en memoria. Para acceder a la consola:

- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:camionesdb`
- Username: `sa`
- Password: (vacío)

## 🔑 Endpoints

Todos los endpoints están bajo `/api`

### Autenticación
- POST `/api/auth/register` - Registro de usuario
- POST `/api/auth/login` - Inicio de sesión

### Camiones (requieren autenticación)
- GET `/api/camiones` - Listar camiones del usuario
- POST `/api/camiones` - Crear camión
- DELETE `/api/camiones/{id}` - Eliminar camión

## 📦 Estructura del Proyecto

```
src/main/java/com/camiones/
├── controller/          # Controladores REST
├── service/            # Lógica de negocio
├── model/              # Entidades JPA
├── repository/         # Repositorios JPA
├── dto/                # Data Transfer Objects
└── util/               # Utilidades (JWT)
```
