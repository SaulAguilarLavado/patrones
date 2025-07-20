package com.ods14.patrones.model.procedimientos;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.ods14.patrones.model.conexion.ConexionBD;

public class ProcedimientoInsertEventoRecaudacion implements ProcedimientoPrototype {
    private final String nombreProcedimiento = "sp_insert_evento_recaudacion";

    @Override
    public ProcedimientoPrototype clonar() {
        return new ProcedimientoInsertEventoRecaudacion();
    }

    @Override
    public boolean validarParametros(Object... parametros) {
        return parametros.length == 8 && 
               parametros[0] instanceof String &&    // nombre_evento
               parametros[1] instanceof BigDecimal && // meta_recaudacion
               parametros[2] instanceof String &&    // fecha_evento
               parametros[3] instanceof String &&    // lugar_evento
               parametros[4] instanceof String &&    // tipo_evento
               parametros[5] instanceof BigDecimal && // gastos_estimados
               parametros[6] instanceof Integer &&   // id_usuario_organizador
               parametros[7] instanceof Integer;     // id_campana
    }

    @Override
    public String getDescripcionParametros() {
        return "Se requieren 8 parámetros: nombre_evento (String), meta_recaudacion (BigDecimal), " +
               "fecha_evento (String), lugar_evento (String), tipo_evento (String), " +
               "gastos_estimados (BigDecimal), id_usuario_organizador (Integer), id_campana (Integer)";
    }

    @Override
    public void ejecutar(Object... parametros) {
        if (!validarParametros(parametros)) {
            throw new IllegalArgumentException("Error en " + getTipo() + ": " + getDescripcionParametros());
        }
        
        Connection conn = ConexionBD.getInstancia().getConexion();
        try (CallableStatement stmt = conn.prepareCall("{call " + nombreProcedimiento + "(?, ?, ?, ?, ?, ?, ?, ?)}")) {
            stmt.setString(1, (String) parametros[0]);
            stmt.setBigDecimal(2, (BigDecimal) parametros[1]);
            stmt.setString(3, (String) parametros[2]);
            stmt.setString(4, (String) parametros[3]);
            stmt.setString(5, (String) parametros[4]);
            stmt.setBigDecimal(6, (BigDecimal) parametros[5]);
            stmt.setInt(7, (Integer) parametros[6]);
            stmt.setInt(8, (Integer) parametros[7]);
            stmt.execute();
            System.out.println("Evento de recaudación insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar evento de recaudación: " + e.getMessage());
        }
    }
}