/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Coder
 * Created: 31 ago 2026
 */
-- Eliminar tablas si existen (en orden por llaves foráneas)
DROP TABLE IF EXISTS ordenes_de_servicio;
DROP TABLE IF EXISTS vehiculos;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS repuestos;
DROP TABLE IF EXISTS usuarios;

-- 1. Tabla Clientes
CREATE TABLE clientes (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    tipo_identificacion VARCHAR(20) NOT NULL,
    numero_identificacion VARCHAR(30) NOT NULL UNIQUE,
    nombre_completo VARCHAR(150) NOT NULL,
    telefono VARCHAR(30) NOT NULL,
    correo VARCHAR(150) UNIQUE,
    direccion VARCHAR(200),
    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
    fecha_registro DATE NOT NULL DEFAULT CURRENT_DATE
);

-- 2. Tabla Vehículos (Corregida de veterinaria a taller automotriz)
CREATE TABLE vehiculos (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    placa VARCHAR(10) NOT NULL UNIQUE,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    cliente_id INT NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
    fecha_registro DATE NOT NULL DEFAULT CURRENT_DATE,
    CONSTRAINT fk_vehiculos_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);

-- 3. Tabla Usuarios
CREATE TABLE usuarios (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO'
);

-- 4. Tabla Repuestos
CREATE TABLE repuestos (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo_referencia VARCHAR(80) NOT NULL UNIQUE,
    nombre VARCHAR(150) NOT NULL,
    presentacion VARCHAR(100) NOT NULL,
    proveedor VARCHAR(100),
    stock_total INT NOT NULL DEFAULT 0,
    stock_disponible INT NOT NULL DEFAULT 0,
    precio_unitario DOUBLE PRECISION NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
    fecha_registro DATE NOT NULL DEFAULT CURRENT_DATE
);

-- 5. Tabla Órdenes de Servicio
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
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_ordenes_vehiculo FOREIGN KEY (vehiculo_id) REFERENCES vehiculos(id),
    CONSTRAINT fk_ordenes_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    CONSTRAINT fk_ordenes_repuesto FOREIGN KEY (repuesto_utilizado) REFERENCES repuestos(id)
);

-- Cargas Iniciales
INSERT INTO usuarios (nombre_usuario, contrasena, rol, estado) 
VALUES ('admin', 'admin123', 'ADMIN', 'ACTIVO');

INSERT INTO repuestos (codigo_referencia, nombre, presentacion, proveedor, stock_total, stock_disponible, precio_unitario) 
VALUES ('REP-001', 'Filtro de Aceite', 'Unidad', 'Bosch', 50, 50, 25.0);



