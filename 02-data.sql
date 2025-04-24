-- Forzar codificación correcta
SET NAMES utf8mb4 COLLATE utf8mb4_general_ci;

-- Insertar roles si no existen
INSERT IGNORE INTO roles (id, tipo) VALUES (1, 'GESTOR'), (2, 'ADMIN');

-- Insertar usuarios de prueba
INSERT INTO usuarios (id, nombre, email, password_hash, rol_id) VALUES
  (1, 'Laura', 'laura@celiahelp.com', '$2a$10$XP4zLDySECWG83RpYoN3/.1lGRHj3k1s7ocdE6VXvSeDd6Uwvw0be', 1),
  (2, 'Juan', 'juan@celiahelp.com', '$2a$10$XP4zLDySECWG83RpYoN3/.1lGRHj3k1s7ocdE6VXvSeDd6Uwvw0be', 2),
  (3, 'Admin', 'admin@celiahelp.com', '$2a$10$XP4zLDySECWG83RpYoN3/.1lGRHj3k1s7ocdE6VXvSeDd6Uwvw0be', 1);

-- Insertar incidencias de ejemplo
INSERT INTO incidencias (id, titulo, descripcion, prioridad, estado, nombre_remitente, email_remitente, gestionada_por)
VALUES
  (1, 'No funciona Internet', 'Desde las 9h no hay conexión en el aula de redes.', 'ALTA', 'PENDIENTE', 'Carlos', 'carlos@iescelia.com', 1),
  (2, 'Problema con impresora', 'No imprime en red. Se ha reiniciado varias veces.', 'MEDIA', 'PENDIENTE', 'Sofía', 'sofia@iescelia.com', NULL),
  (3, 'Fallo de acceso a Moodle', 'El alumnado no puede acceder a la plataforma Moodle.', 'ALTA', 'COMPLETADA', 'Manuel', 'manuel@iescelia.com', 2);

-- Insertar logs de prueba
INSERT INTO logs (incidencia_id, usuario_id, fecha, accion)
VALUES
  (1, 1, NOW(), 'CREADA'),
  (1, 1, NOW(), 'ACTUALIZADA'),
  (3, 2, NOW(), 'CERRADA');
