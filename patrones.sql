-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS patrones;
USE patrones;

-- ===== TABLAS EXISTENTES =====
-- Tabla de usuarios (sin cambios)
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL
);

-- Tabla de contribuciones (sin cambios)
CREATE TABLE IF NOT EXISTS contribucion (
    id_contribucion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    tipo_contribucion ENUM('actividad', 'campaña', 'donacion') NOT NULL,
    metodo_pago ENUM('tarjeta', 'paypal', 'transferencia') DEFAULT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabla de ecosistemas (sin cambios)
CREATE TABLE IF NOT EXISTS ecosistema (
    id_ecosistema INT AUTO_INCREMENT PRIMARY KEY,
    nombre_ecosistema VARCHAR(100) NOT NULL
);

-- Tabla de especies (sin cambios)
CREATE TABLE IF NOT EXISTS especie (
    id_especie INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cientifico VARCHAR(100) NOT NULL,
    nombre_comun VARCHAR(100),
    estado_conservacion ENUM('Preocupación Menor', 'Vulnerable', 'En Peligro', 'En Peligro Crítico'),
    descripcion TEXT,
    id_ecosistema INT,
    FOREIGN KEY (id_ecosistema) REFERENCES ecosistema(id_ecosistema)
);

-- Tabla de biodiversidad (sin cambios)
CREATE TABLE IF NOT EXISTS biodiversidad (
    id_biodiversidad INT AUTO_INCREMENT PRIMARY KEY,
    id_ecosistema INT,
    descripcion TEXT,
    FOREIGN KEY (id_ecosistema) REFERENCES ecosistema(id_ecosistema)
);

-- Tabla de actividades (sin cambios)
CREATE TABLE IF NOT EXISTS actividad (
    id_actividad INT AUTO_INCREMENT PRIMARY KEY,
    nombre_actividad VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    lugar VARCHAR(100) NOT NULL,
    responsable VARCHAR(100) NOT NULL,
    descripcion TEXT
);

-- Tabla de participantes en actividades (sin cambios)
CREATE TABLE IF NOT EXISTS participante_actividad (
    id_participante INT AUTO_INCREMENT PRIMARY KEY,
    id_actividad INT,
    nombre_participante VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_actividad) REFERENCES actividad(id_actividad)
);

-- Tabla de reportes de impacto (sin cambios)
CREATE TABLE IF NOT EXISTS reporte_impacto (
    id_reporte INT AUTO_INCREMENT PRIMARY KEY,
    id_actividad INT,
    impacto_logrado TEXT,
    resultados_cuantificables TEXT,
    residuos_recolectados INT DEFAULT 0,
    especies_monitoreadas INT DEFAULT 0,
    fecha_reporte DATE,
    id_usuario INT,
    FOREIGN KEY (id_actividad) REFERENCES actividad(id_actividad),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- ===== NUEVAS TABLAS PARA SENSIBILIZACIÓN Y RECAUDACIÓN =====

-- 1. TABLA DE CAMPAÑAS
CREATE TABLE IF NOT EXISTS campana (
    id_campana INT AUTO_INCREMENT PRIMARY KEY,
    nombre_campana VARCHAR(200) NOT NULL,
    objetivo_principal TEXT NOT NULL,
    publico_objetivo VARCHAR(200) NOT NULL,
    mensaje_clave TEXT NOT NULL,
    duracion_dias INT NOT NULL,
    medios_utilizados TEXT NOT NULL,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('activa', 'pausada', 'finalizada') DEFAULT 'activa',
    id_usuario_creador INT,
    FOREIGN KEY (id_usuario_creador) REFERENCES usuario(id_usuario)
);

-- 2. TABLA DE DONACIONES
CREATE TABLE IF NOT EXISTS donacion (
    id_donacion INT AUTO_INCREMENT PRIMARY KEY,
    nombre_donante VARCHAR(100) NOT NULL,
    monto_donacion DECIMAL(10,2) NOT NULL,
    tipo_donacion ENUM('unica', 'recurrente') NOT NULL,
    proyecto_especifico VARCHAR(200),
    reconocimiento ENUM('publico', 'anonimo', 'con_nombre') DEFAULT 'con_nombre',
    fecha_donacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    metodo_pago ENUM('tarjeta', 'paypal', 'transferencia') NOT NULL,
    id_usuario INT,
    id_campana INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_campana) REFERENCES campana(id_campana)
);

-- 3. TABLA DE EVENTOS DE RECAUDACIÓN
CREATE TABLE IF NOT EXISTS evento_recaudacion (
    id_evento INT AUTO_INCREMENT PRIMARY KEY,
    nombre_evento VARCHAR(200) NOT NULL,
    meta_recaudacion DECIMAL(10,2) NOT NULL,
    fecha_evento DATE NOT NULL,
    lugar_evento VARCHAR(200) NOT NULL,
    tipo_evento ENUM('conferencia', 'concierto', 'subasta', 'maratón', 'otro') NOT NULL,
    gastos_estimados DECIMAL(10,2) DEFAULT 0,
    recaudacion_actual DECIMAL(10,2) DEFAULT 0,
    estado ENUM('planificado', 'en_progreso', 'finalizado', 'cancelado') DEFAULT 'planificado',
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_usuario_organizador INT,
    id_campana INT,
    FOREIGN KEY (id_usuario_organizador) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_campana) REFERENCES campana(id_campana)
);

-- 4. TABLA DE PARTICIPANTES EN EVENTOS
CREATE TABLE IF NOT EXISTS participante_evento (
    id_participante_evento INT AUTO_INCREMENT PRIMARY KEY,
    id_evento INT,
    nombre_participante VARCHAR(100) NOT NULL,
    tipo_participacion ENUM('asistente', 'voluntario', 'patrocinador') DEFAULT 'asistente',
    contribucion_monetaria DECIMAL(10,2) DEFAULT 0,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_evento) REFERENCES evento_recaudacion(id_evento)
);

-- 5. TABLA DE PROYECTOS FINANCIADOS
CREATE TABLE IF NOT EXISTS proyecto_financiado (
    id_proyecto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_proyecto VARCHAR(200) NOT NULL,
    descripcion TEXT,
    monto_objetivo DECIMAL(10,2) NOT NULL,
    monto_recaudado DECIMAL(10,2) DEFAULT 0,
    estado ENUM('activo', 'completado', 'pausado') DEFAULT 'activo',
    fecha_inicio DATE,
    fecha_fin_estimada DATE,
    id_campana INT,
    FOREIGN KEY (id_campana) REFERENCES campana(id_campana)
);

-- ===== PROCEDIMIENTOS ALMACENADOS EXISTENTES (sin cambios) =====
DELIMITER //

-- CRUD para usuario (sin cambios)
DROP PROCEDURE IF EXISTS sp_insert_usuario //
CREATE PROCEDURE sp_insert_usuario(IN p_nombre_usuario VARCHAR(50), IN p_contrasena VARCHAR(255))
BEGIN
    INSERT INTO usuario (nombre_usuario, contrasena)
    VALUES (p_nombre_usuario, p_contrasena);
END //

DROP PROCEDURE IF EXISTS sp_get_usuario //
CREATE PROCEDURE sp_get_usuario(IN p_nombre_usuario VARCHAR(50))
BEGIN
    SELECT * FROM usuario WHERE nombre_usuario = p_nombre_usuario;
END //

-- Procedimientos existentes para ecosistema, especie, biodiversidad, actividad, etc. (sin cambios)
DROP PROCEDURE IF EXISTS sp_insert_ecosistema //
CREATE PROCEDURE sp_insert_ecosistema(IN p_nombre_ecosistema VARCHAR(100))
BEGIN
    INSERT INTO ecosistema (nombre_ecosistema) VALUES (p_nombre_ecosistema);
END //

DROP PROCEDURE IF EXISTS sp_insert_especie //
CREATE PROCEDURE sp_insert_especie(
    IN p_nombre_cientifico VARCHAR(100),
    IN p_nombre_comun VARCHAR(100),
    IN p_estado ENUM('Preocupación Menor', 'Vulnerable', 'En Peligro', 'En Peligro Crítico'),
    IN p_descripcion TEXT,
    IN p_id_ecosistema INT
)
BEGIN
    INSERT INTO especie (nombre_cientifico, nombre_comun, estado_conservacion, descripcion, id_ecosistema)
    VALUES (p_nombre_cientifico, p_nombre_comun, p_estado, p_descripcion, p_id_ecosistema);
END //

DROP PROCEDURE IF EXISTS sp_insert_biodiversidad //
CREATE PROCEDURE sp_insert_biodiversidad(IN p_id_ecosistema INT, IN p_descripcion TEXT)
BEGIN
    INSERT INTO biodiversidad (id_ecosistema, descripcion) VALUES (p_id_ecosistema, p_descripcion);
END //

DROP PROCEDURE IF EXISTS sp_insert_actividad //
CREATE PROCEDURE sp_insert_actividad(
    IN p_nombre_actividad VARCHAR(100),
    IN p_fecha DATE,
    IN p_hora_inicio TIME,
    IN p_lugar VARCHAR(100),
    IN p_responsable VARCHAR(100),
    IN p_descripcion TEXT
)
BEGIN
    INSERT INTO actividad (nombre_actividad, fecha, hora_inicio, lugar, responsable, descripcion)
    VALUES (p_nombre_actividad, p_fecha, p_hora_inicio, p_lugar, p_responsable, p_descripcion);
END //

DROP PROCEDURE IF EXISTS sp_insert_participante_actividad //
CREATE PROCEDURE sp_insert_participante_actividad(IN p_id_actividad INT, IN p_nombre_participante VARCHAR(100))
BEGIN
    INSERT INTO participante_actividad (id_actividad, nombre_participante)
    VALUES (p_id_actividad, p_nombre_participante);
END //

DROP PROCEDURE IF EXISTS sp_insert_reporte_impacto //
CREATE PROCEDURE sp_insert_reporte_impacto(
    IN p_id_actividad INT,
    IN p_impacto_logrado TEXT,
    IN p_resultados_cuantificables TEXT,
    IN p_residuos_recolectados INT,
    IN p_especies_monitoreadas INT,
    IN p_id_usuario INT
)
BEGIN
    INSERT INTO reporte_impacto (
        id_actividad, impacto_logrado, resultados_cuantificables, 
        residuos_recolectados, especies_monitoreadas, id_usuario
    ) VALUES (
        p_id_actividad, p_impacto_logrado, p_resultados_cuantificables, 
        p_residuos_recolectados, p_especies_monitoreadas, p_id_usuario
    );
END //

-- ===== NUEVOS PROCEDIMIENTOS PARA SENSIBILIZACIÓN Y RECAUDACIÓN =====

-- 1. PROCEDIMIENTOS PARA CAMPAÑAS
DROP PROCEDURE IF EXISTS sp_insert_campana //
CREATE PROCEDURE sp_insert_campana(
    IN p_nombre_campana VARCHAR(200),
    IN p_objetivo_principal TEXT,
    IN p_publico_objetivo VARCHAR(200),
    IN p_mensaje_clave TEXT,
    IN p_duracion_dias INT,
    IN p_medios_utilizados TEXT,
    IN p_id_usuario_creador INT
)
BEGIN
    INSERT INTO campana (
        nombre_campana, objetivo_principal, publico_objetivo, 
        mensaje_clave, duracion_dias, medios_utilizados, id_usuario_creador
    ) VALUES (
        p_nombre_campana, p_objetivo_principal, p_publico_objetivo, 
        p_mensaje_clave, p_duracion_dias, p_medios_utilizados, p_id_usuario_creador
    );
END //

DROP PROCEDURE IF EXISTS sp_get_campanas_activas //
CREATE PROCEDURE sp_get_campanas_activas()
BEGIN
    SELECT * FROM campana WHERE estado = 'activa' ORDER BY fecha_creacion DESC;
END //

-- 2. PROCEDIMIENTOS PARA DONACIONES
DROP PROCEDURE IF EXISTS sp_insert_donacion //
CREATE PROCEDURE sp_insert_donacion(
    IN p_nombre_donante VARCHAR(100),
    IN p_monto_donacion DECIMAL(10,2),
    IN p_tipo_donacion ENUM('unica', 'recurrente'),
    IN p_proyecto_especifico VARCHAR(200),
    IN p_reconocimiento ENUM('publico', 'anonimo', 'con_nombre'),
    IN p_metodo_pago ENUM('tarjeta', 'paypal', 'transferencia'),
    IN p_id_usuario INT,
    IN p_id_campana INT
)
BEGIN
    INSERT INTO donacion (
        nombre_donante, monto_donacion, tipo_donacion, proyecto_especifico, 
        reconocimiento, metodo_pago, id_usuario, id_campana
    ) VALUES (
        p_nombre_donante, p_monto_donacion, p_tipo_donacion, p_proyecto_especifico, 
        p_reconocimiento, p_metodo_pago, p_id_usuario, p_id_campana
    );
END //

-- 3. PROCEDIMIENTOS PARA EVENTOS DE RECAUDACIÓN
DROP PROCEDURE IF EXISTS sp_insert_evento_recaudacion //
CREATE PROCEDURE sp_insert_evento_recaudacion(
    IN p_nombre_evento VARCHAR(200),
    IN p_meta_recaudacion DECIMAL(10,2),
    IN p_fecha_evento DATE,
    IN p_lugar_evento VARCHAR(200),
    IN p_tipo_evento ENUM('conferencia', 'concierto', 'subasta', 'maratón', 'otro'),
    IN p_gastos_estimados DECIMAL(10,2),
    IN p_id_usuario_organizador INT,
    IN p_id_campana INT
)
BEGIN
    INSERT INTO evento_recaudacion (
        nombre_evento, meta_recaudacion, fecha_evento, lugar_evento, 
        tipo_evento, gastos_estimados, id_usuario_organizador, id_campana
    ) VALUES (
        p_nombre_evento, p_meta_recaudacion, p_fecha_evento, p_lugar_evento, 
        p_tipo_evento, p_gastos_estimados, p_id_usuario_organizador, p_id_campana
    );
END //

-- 4. PROCEDIMIENTO PARA ESTADÍSTICAS DE DONACIONES
DROP PROCEDURE IF EXISTS sp_estadisticas_donaciones //
CREATE PROCEDURE sp_estadisticas_donaciones()
BEGIN
    SELECT
        (SELECT COALESCE(SUM(monto_donacion), 0) FROM donacion) AS total_recaudado,
        (SELECT COUNT(DISTINCT id_usuario) FROM donacion WHERE id_usuario IS NOT NULL) AS numero_donantes,
        (SELECT COUNT(*) FROM campana WHERE estado = 'activa') AS campanas_activas,
        (SELECT COUNT(*) FROM evento_recaudacion WHERE estado = 'finalizado') AS eventos_realizados,
        (SELECT COUNT(*) FROM proyecto_financiado WHERE estado = 'activo') AS proyectos_financiados;
END //

-- 5. PROCEDIMIENTO PARA ESTADÍSTICAS POR USUARIO
DROP PROCEDURE IF EXISTS sp_estadisticas_donaciones_usuario //
CREATE PROCEDURE sp_estadisticas_donaciones_usuario(IN p_id_usuario INT)
BEGIN
    SELECT
        (SELECT COALESCE(SUM(monto_donacion), 0) FROM donacion WHERE id_usuario = p_id_usuario) AS total_donado,
        (SELECT COUNT(*) FROM donacion WHERE id_usuario = p_id_usuario) AS numero_donaciones,
        (SELECT COUNT(*) FROM campana WHERE id_usuario_creador = p_id_usuario AND estado = 'activa') AS campanas_creadas,
        (SELECT COUNT(*) FROM evento_recaudacion WHERE id_usuario_organizador = p_id_usuario) AS eventos_organizados;
END //

DELIMITER ;

-- ===== DATOS DE EJEMPLO =====
-- Insertar datos base en ecosistema
INSERT IGNORE INTO ecosistema (nombre_ecosistema) VALUES 
('Arrecife de Coral'),
('Mar Abierto'),
('Pradera Marina'),
('Océano Profundo');

-- Datos de ejemplo para campañas
INSERT IGNORE INTO campana (nombre_campana, objetivo_principal, publico_objetivo, mensaje_clave, duracion_dias, medios_utilizados, id_usuario_creador) VALUES 
('Salvemos los Océanos 2024', 'Crear conciencia sobre la contaminación marina', 'Jóvenes de 18-35 años', 'Cada acción cuenta para salvar nuestros océanos', 90, 'Redes sociales, conferencias, eventos', 1),
('Limpieza Global Marina', 'Organizar eventos de limpieza en costas', 'Familias y comunidades costeras', 'Unidos por mares más limpios', 60, 'Medios locales, voluntariado', 1);

-- Datos de ejemplo para proyectos financiados
INSERT IGNORE INTO proyecto_financiado (nombre_proyecto, descripcion, monto_objetivo, id_campana) VALUES 
('Restauración de Arrecifes de Coral', 'Proyecto para restaurar 100 hectáreas de arrecifes dañados', 50000.00, 1),
('Sistema de Monitoreo Marino', 'Implementación de sensores para monitorear la calidad del agua', 75000.00, 2);