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

DELIMITER ;