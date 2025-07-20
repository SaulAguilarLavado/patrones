package com.ods14.patrones.model.procedimientos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.ods14.patrones.model.conexion.ConexionBD;

public class ProcedimientoInsertCampana implements ProcedimientoPrototype {
    private final String nombreProcedimiento = "sp_insert_campana";

    @Override
    public ProcedimientoPrototype clonar() {
        return new ProcedimientoInsertCampana();
    }

    @Override
    public boolean validarParametros(Object... parametros) {
        return parametros.length == 7 && 
               parametros[0] instanceof String &&  // nombre_campana
               parametros[1] instanceof String &&  // objetivo_principal
               parametros[2] instanceof String &&  // publico_objetivo
               parametros[3] instanceof String &&  // mensaje_clave
               parametros[4] instanceof Integer && // duracion_dias
               parametros[5] instanceof String &&  // medios_utilizados
               parametros[6] instanceof Integer;   // id_usuario_creador
    }

    @Override
    public String getDescripcionParametros() {
        return "Se requieren 7 parámetros: nombre_campana (String), objetivo_principal (String), " +
               "publico_objetivo (String), mensaje_clave (String), duracion_dias (Integer), " +
               "medios_utilizados (String), id_usuario_creador (Integer)";
    }

    @Override
    public void ejecutar(Object... parametros) {
        if (!validarParametros(parametros)) {
            throw new IllegalArgumentException("Error en " + getTipo() + ": " + getDescripcionParametros());
        }
        
        Connection conn = ConexionBD.getInstancia().getConexion();
        try (CallableStatement stmt = conn.prepareCall("{call " + nombreProcedimiento + "(?, ?, ?, ?, ?, ?, ?)}")) {
            stmt.setString(1, (String) parametros[0]);
            stmt.setString(2, (String) parametros[1]);
            stmt.setString(3, (String) parametros[2]);
            stmt.setString(4, (String) parametros[3]);
            stmt.setInt(5, (Integer) parametros[4]);
            stmt.setString(6, (String) parametros[5]);
            stmt.setInt(7, (Integer) parametros[6]);
            stmt.execute();
            System.out.println("Campaña insertada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar campaña: " + e.getMessage());
        }
    }
}