# Aplicación de Gestión de Camiones

Proyecto completo con Flutter (frontend) y Java Spring Boot (backend) para gestión de camiones empresariales.

## 📁 Estructura del Proyecto

```
desarrollo_flutter/
├── camionesapp/          # Aplicación Flutter
│   └── camionesapp/
│       ├── lib/
│       │   ├── models/       # Modelos de datos
│       │   ├── services/     # Servicios API
│       │   └── screens/      # Pantallas de la app
│       └── pubspec.yaml
└── backend/              # API REST Java Spring Boot
    ├── src/
    │   └── main/
    │       ├── java/com/camiones/
    │       │   ├── controller/
    │       │   ├── service/
    │       │   ├── model/
    │       │   ├── repository/
    │       │   ├── dto/
    │       │   └── util/
    │       └── resources/
    └── pom.xml
```

## 🚀 Características

### Frontend (Flutter)
- ✅ Autenticación (Login y Registro)
- ✅ Listado de camiones por empresa
- ✅ Agregar nuevos camiones
- ✅ Eliminar camiones
- ✅ Gestión de tokens JWT
- ✅ UI moderna con Material Design

### Backend (Java Spring Boot)
- ✅ API REST completa
- ✅ Autenticación con JWT
- ✅ Base de datos H2 en memoria
- ✅ CRUD de camiones
- ✅ Gestión de usuarios
- ✅ Validaciones

## 📋 Requisitos Previos

### Para Flutter:
- Flutter SDK 3.7.0 o superior
- Dart SDK
- Android Studio / VS Code
- Emulador Android o dispositivo físico

### Para Backend:
- Java JDK 17 o superior
- Maven 3.6 o superior

## 🛠️ Instalación y Ejecución

### 1. Backend (Java Spring Boot)

```bash
# Navegar a la carpeta del backend
cd backend

# Compilar el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

El backend estará disponible en: `http://localhost:8080`

**Consola H2:** `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:camionesdb`
- Username: `sa`
- Password: (dejar vacío)

### 2. Frontend (Flutter)

```bash
# Navegar a la carpeta de Flutter
cd camionesapp/camionesapp

# Instalar dependencias
flutter pub get

# Ejecutar la aplicación
flutter run
```

## 📡 API Endpoints

### Autenticación

**POST** `/api/auth/register`
```json
{
  "username": "usuario1",
  "email": "usuario@example.com",
  "password": "password123",
  "empresa": "Mi Empresa"
}
```

**POST** `/api/auth/login`
```json
{
  "username": "usuario1",
  "password": "password123"
}
```

### Camiones (Requieren token JWT)

**GET** `/api/camiones`
- Header: `Authorization: Bearer {token}`

**POST** `/api/camiones`
```json
{
  "placa": "ABC-123",
  "tipoCamion": "Carga"
}
```
- Header: `Authorization: Bearer {token}`

**DELETE** `/api/camiones/{id}`
- Header: `Authorization: Bearer {token}`

## 🎨 Tipos de Camiones Disponibles

- Carga
- Refrigerado
- Tanque
- Volteo
- Plataforma
- Contenedor

## 🔐 Seguridad

- Autenticación basada en JWT
- Tokens con expiración de 24 horas
- Validación de permisos por usuario
- CORS habilitado para desarrollo

## 📱 Pantallas de la Aplicación

1. **Login Screen** - Inicio de sesión
2. **Register Screen** - Registro de nuevos usuarios
3. **Camiones List Screen** - Listado de camiones
4. **Add Camion Screen** - Agregar nuevo camión

## 🎓 Uso para Clase Magistral

Este proyecto está diseñado para demostrar:

1. **Arquitectura de Flutter:**
   - Separación de responsabilidades (Models, Services, Screens)
   - Gestión de estado con StatefulWidget
   - Navegación entre pantallas

2. **Consumo de APIs REST:**
   - Uso de paquete `http`
   - Manejo de JSON
   - Autenticación con tokens
   - Gestión de headers

3. **Backend Spring Boot:**
   - Arquitectura MVC
   - JPA/Hibernate
   - Base de datos H2
   - JWT para autenticación

## 🐛 Troubleshooting

### Error de conexión en Flutter
- Verificar que el backend esté corriendo en `http://localhost:8080`
- En emulador Android, usar `http://10.0.2.2:8080` en lugar de `localhost`
- En dispositivo físico, usar la IP de tu computadora

### Backend no inicia
- Verificar que el puerto 8080 esté disponible
- Revisar que Java 17+ esté instalado: `java -version`
- Verificar Maven: `mvn -version`

## 📝 Notas para la Clase

- El proyecto usa H2 en memoria, los datos se pierden al reiniciar
- Las contraseñas se guardan en texto plano (solo para demostración)
- CORS está abierto a todos los orígenes (solo para desarrollo)
- Para producción, implementar bcrypt y configurar CORS apropiadamente

## 👨‍💻 Autor

Proyecto creado para clase magistral de desarrollo móvil con Flutter y APIs REST.
