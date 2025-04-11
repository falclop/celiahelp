-- Fuerza codificación de la conexión
SET NAMES utf8mb4 COLLATE utf8mb4_general_ci;

-- Crear base de datos
CREATE DATABASE IF NOT EXISTS celiaHelp
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;
USE celiaHelp;
-- Crear usuario y permisos
CREATE USER IF NOT EXISTS 'celiahelp'@'%' IDENTIFIED BY 'celiahelp';
GRANT ALL PRIVILEGES ON celiaHelp.* TO 'celiahelp'@'%';
FLUSH PRIVILEGES;

-- Tabla de roles
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('GESTOR', 'ADMIN') DEFAULT 'GESTOR'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla de usuarios
CREATE TABLE usuarios (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  rol_id INT,
  FOREIGN KEY (rol_id) REFERENCES roles(id) ON DELETE CASCADE,
  KEY idx_rol_id (rol_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla de incidencias
CREATE TABLE incidencias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    prioridad ENUM('BAJA', 'MEDIA', 'ALTA') DEFAULT 'BAJA',
    estado ENUM('PENDIENTE','COMPLETADA') DEFAULT 'PENDIENTE',
    nombre_remitente VARCHAR(100) NOT NULL,
    email_remitente VARCHAR(100) NOT NULL,
    gestionada_por INT,
    FOREIGN KEY (gestionada_por) REFERENCES usuarios(id) ON DELETE SET NULL,
    KEY idx_gestionada_por (gestionada_por),
    KEY idx_fecha_creacion (fecha_creacion)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabla de logs
CREATE TABLE logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incidencia_id INT,
    usuario_id INT,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    accion ENUM('CREADA', 'ACTUALIZADA', 'CERRADA') NOT NULL,
    FOREIGN KEY (incidencia_id) REFERENCES incidencias(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    KEY idx_incidencia_usuario (incidencia_id, usuario_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
