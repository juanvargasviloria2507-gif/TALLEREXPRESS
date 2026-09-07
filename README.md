# TallerExpress

Aplicación de escritorio para la gestión de un taller automotriz, desarrollada con Java 17, Swing (`JOptionPane`) y PostgreSQL. Permite administrar clientes, vehículos, repuestos, usuarios y órdenes de servicio.

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

La aplicación utiliza una arquitectura por capas:

- `presentation`: muestra los menús y diálogos de Swing.
- `controller`: agrupa las operaciones de cada módulo.
- `service`: contiene la lógica de negocio.
- `dao`: define el acceso a datos y sus implementaciones.
- `model`: representa las entidades del sistema.
- `config`: contiene la conexión a PostgreSQL.
- `exception`: define excepciones de negocio y persistencia.
- `util`: incluye utilidades para registro y formato de tablas.

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- Java 17 o superior
- Maven 3.8 o superior
- PostgreSQL 12 o superior
- Acceso a una base de datos local con permisos para crear tablas e insertar datos

## Base de datos

El proyecto usa PostgreSQL. La configuración actual está definida directamente en `DatabaseConnection.java`:

- URL: `jdbc:postgresql://localhost:5432/TallerExpress`
- Usuario: `postgres`
- Contraseña: valor definido en la constante `PASSWORD`

> La contraseña está hardcodeada actualmente. Para otro entorno, cambia `URL`, `USER` y `PASSWORD` antes de ejecutar la aplicación. No reutilices credenciales reales en el repositorio.
>
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

## Uso de la aplicación

La interfaz se desarrolla mediante cuadros de diálogo de Swing (`JOptionPane`). Desde el menú principal se puede acceder a:

- Login de usuario
- Menú principal del taller
- Gestión de clientes y vehículos
- Gestión de repuestos
- Registro de órdenes de servicio
- Mensajes de confirmación y error

El acceso a `Gestión de Usuarios` está restringido al rol `ADMIN`. Los roles disponibles en la base de datos son `ADMIN` y `RECEPCIONISTA`.

## Pruebas

Actualmente no hay clases de prueba en `src/test/java`. La comprobación disponible es compilar el proyecto con:

```bash
mvn clean package
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
│                       │   ├── UsuariosController.java
│                       │   └── VehiculosController.java
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
│                       ├── presentation/
│                       │   ├── ClientePresentation.java
│                       │   ├── OrdenesServicioPresentation.java
│                       │   ├── RepuestoPresentation.java
│                       │   ├── UsuariosPresentation.java
│                       │   └── VehiculosPresentation.java
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
└── target/                  # Generado por Maven; no es código fuente
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
