package com.ods14.patrones.model;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = ConexionBD.getInstancia().getConexion();
        if (conn != null) {
            System.out.println("¡Conexión exitosa a la base de datos!");
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
        ProcedimientoConservacion proc = new ProcedimientoConservacion("sp_insert_usuario", 2);
        proc.ejecutar("usuarioEjemplo", "contrasena123");
    }
}
