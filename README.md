# TallerExpress

Sistema de gestión para un taller automotriz desarrollado en Java con Swing y PostgreSQL. Permite administrar clientes, vehículos, repuestos, usuarios y órdenes de servicio desde una interfaz gráfica basada en `JOptionPane`.

## Datos del Coder

- Nombre: Juan Isaias Vargas Viloria
- Clan: Puerta de Oro
- Correo: juanvargasviloria2507@gmail.com

## Descripción general del sistema

TallerExpress es una aplicación de escritorio orientada a facilitar la administración de un taller mecánico. El sistema está pensado para registrar información clave del negocio y controlar las operaciones diarias del taller.

Entre sus funciones principales se encuentran:

- Autenticación de usuarios con roles.
- Gestión de clientes y vehículos.
- Registro y consulta de repuestos en inventario.
- Registro de órdenes de servicio.
- Consulta de historial por vehículo.
- Control de acceso para usuarios con rol administrador.
- Persistencia de datos en PostgreSQL.

La aplicación utiliza una arquitectura simple por capas:

- `controller`: controla la lógica de interfaz y flujo de acciones.
- `service`: implementa la lógica de negocio.
- `dao`: accede a la base de datos.
- `model`: define las entidades del sistema.
- `config`: almacena la configuración de conexión.

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- Java 17 o superior
- Maven 3.8 o superior
- PostgreSQL 12 o superior
- Acceso a una base de datos local con permisos para crear tablas e insertar datos

## Base de datos

El proyecto usa PostgreSQL con la siguiente configuración por defecto en `DatabaseConnection.java`:

- URL: `jdbc:postgresql://localhost:5432/TallerExpress`
- Usuario: `postgres`
- Contraseña: `Qwe.123*`

> Es importante que la base de datos `TallerExpress` exista antes de ejecutar la aplicación.

## Configuración y ejecución

### 1. Crear la base de datos

Con PostgreSQL disponible, crea la base de datos:

```sql
CREATE DATABASE "TallerExpress";
```

### 2. Ejecutar el script SQL

Importa el contenido de `database/Database.sql` para crear las tablas y datos iniciales.

```bash
psql -U postgres -d TallerExpress -f database/Database.sql
```

### 3. Verificar la conexión

Revisa la clase:

- `src/main/java/com/mycompany/tallerexpress/config/DatabaseConnection.java`

Asegúrate de que la URL, usuario y contraseña coincidan con tu instalación local de PostgreSQL.

### 4. Compilar el proyecto

Desde la raíz del proyecto:

```bash
mvn clean package
```

### 5. Ejecutar la aplicación

```bash
mvn exec:java -Dexec.mainClass=com.mycompany.tallerexpress.TallerExpress
```

O bien, si se ejecuta desde NetBeans o desde un IDE compatible con Maven, puedes correr la clase principal:

- `com.mycompany.tallerexpress.TallerExpress`

### 6. Credenciales iniciales

El script crea un usuario administrador con estas credenciales:

- Usuario: `admin`
- Contraseña: `admin123`

## Capturas de pantalla de JOptionPane

La interfaz principal del sistema se desarrolla mediante cuadros de diálogo de Swing (`JOptionPane`). Algunos ejemplos de pantallas esperadas son:

- Login de usuario
- Menú principal del taller
- Gestión de clientes y vehículos
- Gestión de repuestos
- Registro de órdenes de servicio
- Mensajes de confirmación y error

```

## Árbol de archivos

```text
TallerExpress/
├── database/
│   └── Database.sql
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── mycompany/
│                   └── tallerexpress/
│                       ├── config/
│                       │   └── DatabaseConnection.java
│                       ├── controller/
│                       │   ├── ClienteController.java
│                       │   ├── OrdenesServicioController.java
│                       │   ├── RepuestoController.java
│                       │   └── UsuariosController.java
│                       ├── dao/
│                       │   ├── ClienteDao.java
│                       │   ├── OrdenesServicioDao.java
│                       │   ├── RepuestoDao.java
│                       │   ├── UsuariosDao.java
│                       │   ├── VehiculosDao.java
│                       │   └── impl/
│                       │       ├── ClientesImpl.java
│                       │       ├── OrdenesServicioImpl.java
│                       │       ├── RepuestoImpl.java
│                       │       ├── UsuariosImpl.java
│                       │       └── VehiculosImpl.java
│                       ├── exception/
│                       │   ├── BusinessException.java
│                       │   └── PersistenceException.java
│                       ├── model/
│                       │   ├── Cliente.java
│                       │   ├── OrdenesServicio.java
│                       │   ├── Repuesto.java
│                       │   ├── Usuarios.java
│                       │   └── Vehiculos.java
│                       ├── service/
│                       │   ├── ClienteService.java
│                       │   ├── OrdenesServicioService.java
│                       │   ├── RepuestoService.java
│                       │   ├── UsuariosService.java
│                       │   ├── VehiculosService.java
│                       │   └── impl/
│                       │       ├── ClienteServiceImpl.java
│                       │       ├── OrdenesServicioServiceImpl.java
│                       │       ├── RepuestoServiceImpl.java
│                       │       ├── UsuariosServiceImpl.java
│                       │       └── VehiculosServiceImpl.java
│                       ├── util/
│                       │   ├── HttpLogger.java
│                       │   └── TableFormatter.java
│                       └── TallerExpress.java
├── nb-configuration.xml
├── pom.xml
├── README.md
└── target/
```

## Notas finales

Este proyecto es una base funcional para la gestión de taller automotriz y puede ampliarse con:

- control de pagos,
- historial completo de mantenimientos,
- reportes PDF,
- autenticación con encriptación,
- exportación de datos,
- mejoras de interfaz gráfica.

Para cualquier cambio en la base de datos o en la lógica de negocio, es recomendable mantener la estructura por capas del proyecto para facilitar mantenimiento y escalabilidad.
