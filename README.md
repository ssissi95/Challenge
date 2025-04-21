# Challenge

Challenge Técnico - API RESTful de Registro de Usuarios

Este proyecto implementa una API RESTful para el registro de usuarios, cumpliendo los requisitos del challenge técnico. 
La aplicación permite registrar usuarios con validación de correo y contraseña, guardar múltiples teléfonos, y retorna la información requerida en formato JSON.

# Tecnologías utilizadas
- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database (en memoria)
- Maven
- Postman (para pruebas)

# Instalación y ejecución

# Clonar el repositorio

git clone https://github.com/ssissi95/Challenge.git
cd Challenge

# Ejecutar la aplicación

- mvn spring-boot:run

La aplicación se ejecutará por defecto en:
 http://localhost:8080

# Acceso a la consola H2:

- URL: http://localhost:8080/h2-console

- JDBC URL: jdbc:h2:mem:testdb

- User: sa

- Password: (dejar vacío)

# Pruebas de la API con Postman
Endpoint de registro de usuario
URL: POST http://localhost:8080/api/auth/register

Content-Type: application/json

Body (raw, JSON):

    {

    "name": "Sergio",

    "email": "sergio@email.com",

    "password": "123456",

    "phones": [

    {

    "number": "123456789",

    "citycode": "11",

    "contrycode": "54"

        }

       ]    

    }
- Respuesta esperada (201 Created):

        {
        "id": "uuid-generado",
        "created": "2025-04-18T12:34:56",
        "modified": "2025-04-18T12:34:56",
        "lastLogin": "2025-04-18T12:34:56",
        "token": "uuid-generado",
        "isActive": true
        }


# Detalles del manejo de token
El token generado no es un JWT. Es un UUID generado automáticamente al registrarse. Es un requisito opcional, y en esta implementación solo se devuelve como parte de la respuesta, no se utiliza para autenticación.