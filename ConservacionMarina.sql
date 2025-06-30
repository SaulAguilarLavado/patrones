-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS ConservacionMarina;
USE ConservacionMarina;

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL
);
select * from usuario;

-- Tabla de contribuciones
CREATE TABLE IF NOT EXISTS contribucion (
    id_contribucion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    tipo_contribucion ENUM('actividad', 'campaña', 'donacion') NOT NULL,
    metodo_pago ENUM('tarjeta', 'paypal', 'transferencia') DEFAULT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabla de ecosistemas
CREATE TABLE IF NOT EXISTS ecosistema (
    id_ecosistema INT AUTO_INCREMENT PRIMARY KEY,
    nombre_ecosistema VARCHAR(100) NOT NULL
);
select * from ecosistema;

-- Tabla de especies
CREATE TABLE IF NOT EXISTS especie (
    id_especie INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cientifico VARCHAR(100) NOT NULL,
    nombre_comun VARCHAR(100),
    estado_conservacion ENUM('Preocupación Menor', 'Vulnerable', 'En Peligro', 'En Peligro Crítico'),
    descripcion TEXT,
    id_ecosistema INT,
    FOREIGN KEY (id_ecosistema) REFERENCES ecosistema(id_ecosistema)
);
SELECT * FROM especie;

-- Tabla de biodiversidad
CREATE TABLE IF NOT EXISTS biodiversidad (
    id_biodiversidad INT AUTO_INCREMENT PRIMARY KEY,
    id_ecosistema INT,
    descripcion TEXT,
    FOREIGN KEY (id_ecosistema) REFERENCES ecosistema(id_ecosistema)
);
select*from biodiversidad;

-- Insertar datos base en ecosistema
INSERT INTO ecosistema (nombre_ecosistema) VALUES 
('Arrecife de Coral'),
('Mar Abierto'),
('Pradera Marina'),
('Océano Profundo');

-- Tabla de actividades
CREATE TABLE IF NOT EXISTS actividad (
    id_actividad INT AUTO_INCREMENT PRIMARY KEY,
    nombre_actividad VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    lugar VARCHAR(100) NOT NULL,
    responsable VARCHAR(100) NOT NULL,
    descripcion TEXT
);

-- Tabla de participantes en actividades
CREATE TABLE IF NOT EXISTS participante_actividad (
    id_participante INT AUTO_INCREMENT PRIMARY KEY,
    id_actividad INT,
    nombre_participante VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_actividad) REFERENCES actividad(id_actividad)
);

-- Tabla de reportes de impacto
CREATE TABLE IF NOT EXISTS reporte_impacto (
    id_reporte INT AUTO_INCREMENT PRIMARY KEY,
    id_actividad INT,
    impacto_logrado TEXT,
    resultados_cuantificables TEXT,
    residuos_recolectados INT DEFAULT 0,
    especies_monitoreadas INT DEFAULT 0,
    fecha_reporte DATE,
    FOREIGN KEY (id_actividad) REFERENCES actividad(id_actividad)
);

-- Procedimientos almacenados
DELIMITER //

-- CRUD para usuario
CREATE PROCEDURE sp_insert_usuario(IN p_nombre_usuario VARCHAR(50), IN p_contrasena VARCHAR(255))
BEGIN
    INSERT INTO usuario (nombre_usuario, contrasena)
    VALUES (p_nombre_usuario, p_contrasena);
END //

CREATE PROCEDURE sp_update_usuario(IN p_id INT, IN p_nombre_usuario VARCHAR(50), IN p_contrasena VARCHAR(255))
BEGIN
    UPDATE usuario
    SET nombre_usuario = p_nombre_usuario,
        contrasena = p_contrasena
    WHERE id_usuario = p_id;
END //

CREATE PROCEDURE sp_delete_usuario(IN p_id INT)
BEGIN
    DELETE FROM usuario WHERE id_usuario = p_id;
END //

CREATE PROCEDURE sp_get_usuario(IN p_nombre_usuario VARCHAR(50))
BEGIN
    SELECT * FROM usuario WHERE nombre_usuario = p_nombre_usuario;
END //

-- CRUD para contribucion
CREATE PROCEDURE sp_insert_contribucion(
    IN p_id_usuario INT,
    IN p_tipo ENUM('actividad', 'campaña', 'donacion'),
    IN p_metodo_pago ENUM('tarjeta', 'paypal', 'transferencia')
)
BEGIN
    INSERT INTO contribucion (id_usuario, tipo_contribucion, metodo_pago)
    VALUES (p_id_usuario, p_tipo, p_metodo_pago);
END //

CREATE PROCEDURE sp_get_contribuciones_por_usuario(IN p_id_usuario INT)
BEGIN
    SELECT * FROM contribucion WHERE id_usuario = p_id_usuario;
END //

-- Procedimientos para especie
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

CREATE PROCEDURE sp_get_especies_por_ecosistema(IN p_id_ecosistema INT)
BEGIN
    SELECT * FROM especie WHERE id_ecosistema = p_id_ecosistema;
END //

-- Procedimientos para biodiversidad
CREATE PROCEDURE sp_insert_biodiversidad(
    IN p_id_ecosistema INT,
    IN p_descripcion TEXT
)
BEGIN
    INSERT INTO biodiversidad (id_ecosistema, descripcion)
    VALUES (p_id_ecosistema, p_descripcion);
END //

CREATE PROCEDURE sp_get_biodiversidad_por_ecosistema(IN p_id_ecosistema INT)
BEGIN
    SELECT * FROM biodiversidad WHERE id_ecosistema = p_id_ecosistema;
END //

-- Procedimiento para ecosistema
CREATE PROCEDURE sp_insert_ecosistema(
    IN p_nombre_ecosistema VARCHAR(100)
)
BEGIN
    INSERT INTO ecosistema (nombre_ecosistema)
    VALUES (p_nombre_ecosistema);
END //


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

CREATE PROCEDURE sp_get_actividades()
BEGIN
    SELECT * FROM actividad;
END //

CREATE PROCEDURE sp_insert_participante_actividad(
    IN p_id_actividad INT,
    IN p_nombre_participante VARCHAR(100)
)
BEGIN
    INSERT INTO participante_actividad (id_actividad, nombre_participante)
    VALUES (p_id_actividad, p_nombre_participante);
END //

CREATE PROCEDURE sp_get_participantes_por_actividad(
    IN p_id_actividad INT
)
BEGIN
    SELECT * FROM participante_actividad WHERE id_actividad = p_id_actividad;
END //

-- Procedimientos para reporte de impacto
CREATE PROCEDURE sp_insert_reporte_impacto(
    IN p_id_actividad INT,
    IN p_impacto_logrado TEXT,
    IN p_resultados_cuantificables TEXT,
    IN p_residuos_recolectados INT,
    IN p_especies_monitoreadas INT
)
BEGIN
    INSERT INTO reporte_impacto (id_actividad, impacto_logrado, resultados_cuantificables, residuos_recolectados, especies_monitoreadas)
    VALUES (p_id_actividad, p_impacto_logrado, p_resultados_cuantificables, p_residuos_recolectados, p_especies_monitoreadas);
END //

CREATE PROCEDURE sp_get_reportes_impacto()
BEGIN
    SELECT r.*, a.nombre_actividad, a.fecha, a.responsable
    FROM reporte_impacto r
    JOIN actividad a ON r.id_actividad = a.id_actividad;
END //

-- Estadísticas de impacto
CREATE PROCEDURE sp_estadisticas_impacto()
BEGIN
    SELECT
        (SELECT COUNT(*) FROM actividad) AS actividades_realizadas,
        (SELECT COUNT(*) FROM participante_actividad) AS participacion_totales,
        (SELECT IFNULL(SUM(residuos_recolectados),0) FROM reporte_impacto) AS residuos_recolectados,
        (SELECT IFNULL(SUM(especies_monitoreadas),0) FROM reporte_impacto) AS especies_monitoreadas;
END //

DELIMITER ;