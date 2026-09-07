/*
 * TallerExpress
 * Base de datos PostgreSQL
 */

/*
 * ==========================================
 * ELIMINAR TABLAS EXISTENTES
 * ==========================================
 */

DROP TABLE IF EXISTS ordenes_de_servicio;
DROP TABLE IF EXISTS vehiculos;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS repuestos;
DROP TABLE IF EXISTS usuarios;


/*
 * ==========================================
 * 1. TABLA CLIENTES
 * ==========================================
 */

CREATE TABLE clientes (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    tipo_identificacion VARCHAR(20) NOT NULL,

    numero_identificacion VARCHAR(30) NOT NULL UNIQUE,

    nombre_completo VARCHAR(150) NOT NULL,

    telefono VARCHAR(30) NOT NULL,

    correo VARCHAR(150) UNIQUE,

    direccion VARCHAR(200),

    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',

    fecha_registro DATE NOT NULL DEFAULT CURRENT_DATE,

    -- Campo agregado
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


/*
 * ==========================================
 * 2. TABLA VEHICULOS
 * ==========================================
 */

CREATE TABLE vehiculos (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    placa VARCHAR(10) NOT NULL UNIQUE,

    marca VARCHAR(50) NOT NULL,

    modelo VARCHAR(50) NOT NULL,

    año INT NOT NULL,

    cliente_id INT NOT NULL,

    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',

    fecha_registro DATE NOT NULL DEFAULT CURRENT_DATE,

    -- Campo agregado
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_vehiculos_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES clientes(id)
        ON DELETE CASCADE
);


/*
 * ==========================================
 * 3. TABLA USUARIOS
 * ==========================================
 */

CREATE TABLE usuarios (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,

    contrasena VARCHAR(255) NOT NULL,

    rol VARCHAR(20) NOT NULL DEFAULT 'RECEPCIONISTA',

    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',

    -- Campo agregado
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_usuario_rol
        CHECK (rol IN ('ADMIN', 'RECEPCIONISTA')),

    CONSTRAINT chk_usuario_estado
        CHECK (estado IN ('ACTIVO', 'INACTIVO'))
);


/*
 * ==========================================
 * 4. TABLA REPUESTOS
 * ==========================================
 */

CREATE TABLE repuestos (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    codigo_referencia VARCHAR(80) NOT NULL UNIQUE,

    nombre VARCHAR(150) NOT NULL,

    -- Campo agregado
    categoria VARCHAR(100) NOT NULL,

    presentacion VARCHAR(100) NOT NULL,

    proveedor VARCHAR(100),

    stock_total INT NOT NULL DEFAULT 0,

    stock_disponible INT NOT NULL DEFAULT 0,

    precio_unitario NUMERIC(12,2) NOT NULL,

    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',

    fecha_registro DATE NOT NULL DEFAULT CURRENT_DATE,

    -- Campo agregado
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_repuesto_stock_total
        CHECK (stock_total >= 0),

    CONSTRAINT chk_repuesto_stock_disponible
        CHECK (stock_disponible >= 0),

    CONSTRAINT chk_repuesto_stock
        CHECK (stock_disponible <= stock_total),

    CONSTRAINT chk_repuesto_precio
        CHECK (precio_unitario >= 0)
);


/*
 * ==========================================
 * 5. TABLA ORDENES DE SERVICIO
 * ==========================================
 */

CREATE TABLE ordenes_de_servicio (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    cliente_id INT NOT NULL,

    vehiculo_id INT NOT NULL,

    repuesto_utilizado INT,

    mecanico_responsable VARCHAR(150) NOT NULL,

    fecha DATE NOT NULL DEFAULT CURRENT_DATE,

    descripcion_problema VARCHAR(200),

    diagnostico VARCHAR(500),

    estado_de_la_orden VARCHAR(20) NOT NULL DEFAULT 'PROGRAMADA',

    costo_total NUMERIC(12,2) DEFAULT 0,

    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_ordenes_vehiculo
        FOREIGN KEY (vehiculo_id)
        REFERENCES vehiculos(id),

    CONSTRAINT fk_ordenes_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES clientes(id),

    CONSTRAINT fk_ordenes_repuesto
        FOREIGN KEY (repuesto_utilizado)
        REFERENCES repuestos(id),

    CONSTRAINT chk_orden_costo
        CHECK (costo_total >= 0),

    CONSTRAINT chk_orden_estado
        CHECK (
            estado_de_la_orden IN (
                'PROGRAMADA',
                'EN_DIAGNOSTICO',
                'EN_REPARACION',
                'FINALIZADA',
                'CANCELADA'
            )
        )
);


/*
 * ==========================================
 * 6. DATOS INICIALES
 * ==========================================
 */

/*
 * Usuario administrador
 */

INSERT INTO usuarios (
    nombre_usuario,
    contrasena,
    rol,
    estado
)
VALUES (
    'admin',
    'admin123',
    'ADMIN',
    'ACTIVO'
);


/*
 * Repuesto inicial
 */

INSERT INTO repuestos (
    codigo_referencia,
    nombre,
    categoria,
    presentacion,
    proveedor,
    stock_total,
    stock_disponible,
    precio_unitario,
    estado
)
VALUES (
    'REP-001',
    'Filtro de Aceite',
    'Filtros',
    'Unidad',
    'Bosch',
    50,
    50,
    25.00,
    'ACTIVO'
);