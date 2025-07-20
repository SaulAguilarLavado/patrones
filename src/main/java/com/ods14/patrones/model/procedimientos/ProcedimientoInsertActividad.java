package com.ods14.patrones.model.procedimientos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.ods14.patrones.model.conexion.ConexionBD;

public class ProcedimientoInsertActividad implements ProcedimientoPrototype {
    private final String nombreProcedimiento = "sp_insert_actividad";

    @Override
    public ProcedimientoPrototype clonar() {
        return new ProcedimientoInsertActividad();
    }

    @Override
    public boolean validarParametros(Object... parametros) {
        return parametros.length == 6 && 
               parametros[0] instanceof String &&  // nombre_actividad
               parametros[1] instanceof String &&  // fecha
               parametros[2] instanceof String &&  // hora_inicio
               parametros[3] instanceof String &&  // lugar
               parametros[4] instanceof String &&  // responsable
               parametros[5] instanceof String;    // descripcion
    }

    @Override
    public String getDescripcionParametros() {
        return "Se requieren 6 parámetros: nombre_actividad (String), fecha (String), " +
               "hora_inicio (String), lugar (String), responsable (String), descripcion (String)";
    }

    @Override
    public void ejecutar(Object... parametros) {
        if (!validarParametros(parametros)) {
            throw new IllegalArgumentException("Error en " + getTipo() + ": " + getDescripcionParametros());
        }
        Connection conn = ConexionBD.getInstancia().getConexion();
        try (CallableStatement stmt = conn.prepareCall("{call " + nombreProcedimiento + "(?, ?, ?, ?, ?, ?)}")) {
            stmt.setString(1, (String) parametros[0]);
            stmt.setString(2, (String) parametros[1]);
            stmt.setString(3, (String) parametros[2]);
            stmt.setString(4, (String) parametros[3]);
            stmt.setString(5, (String) parametros[4]);
            stmt.setString(6, (String) parametros[5]);
            stmt.execute();
            System.out.println("Actividad insertada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}