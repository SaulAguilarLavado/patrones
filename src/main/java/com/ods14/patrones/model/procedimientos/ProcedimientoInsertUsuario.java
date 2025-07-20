package com.ods14.patrones.model.procedimientos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.ods14.patrones.model.conexion.ConexionBD;

public class ProcedimientoInsertUsuario implements ProcedimientoPrototype {
    private final String nombreProcedimiento = "sp_insert_usuario";

    @Override
    public ProcedimientoPrototype clonar() {
        return new ProcedimientoInsertUsuario();
    }

    @Override
    public boolean validarParametros(Object... parametros) {
        return parametros.length == 2 && 
               parametros[0] instanceof String && 
               parametros[1] instanceof String;
    }

    @Override
    public String getDescripcionParametros() {
        return "Se requieren 2 parámetros: nombre_usuario (String), contrasena (String)";
    }

    @Override
    public void ejecutar(Object... parametros) {
        if (!validarParametros(parametros)) {
            throw new IllegalArgumentException("Error en " + getTipo() + ": " + getDescripcionParametros());
        }
        
        Connection conn = ConexionBD.getInstancia().getConexion();
        try (CallableStatement stmt = conn.prepareCall("{call " + nombreProcedimiento + "(?, ?)}")) {
            stmt.setString(1, (String) parametros[0]);
            stmt.setString(2, (String) parametros[1]);
            stmt.execute();
            System.out.println("Usuario insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar usuario: " + e.getMessage());
        }
    }
}