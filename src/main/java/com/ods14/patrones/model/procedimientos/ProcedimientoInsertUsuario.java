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
    public void ejecutar(Object... parametros) {
        if (parametros.length != 2) {
            throw new IllegalArgumentException("Se requieren 2 parámetros: nombre_usuario y contrasena");
        }
        Connection conn = ConexionBD.getInstancia().getConexion();
        try (CallableStatement stmt = conn.prepareCall("{call " + nombreProcedimiento + "(?, ?)}")) {
            stmt.setString(1, (String) parametros[0]);
            stmt.setString(2, (String) parametros[1]);
            stmt.execute();
            System.out.println("Usuario insertado correctamente.");
        } catch (SQLException e) {
            // Lanza la excepción para que el controlador pueda capturarla
            throw new RuntimeException(e);
        }
    }
}