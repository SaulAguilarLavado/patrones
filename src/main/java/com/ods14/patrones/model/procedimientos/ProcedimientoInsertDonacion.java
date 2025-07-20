package com.ods14.patrones.model.procedimientos;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.ods14.patrones.model.conexion.ConexionBD;

public class ProcedimientoInsertDonacion implements ProcedimientoPrototype {
    private final String nombreProcedimiento = "sp_insert_donacion";

    @Override
    public ProcedimientoPrototype clonar() {
        return new ProcedimientoInsertDonacion();
    }

    @Override
    public boolean validarParametros(Object... parametros) {
        return parametros.length == 8 && 
               parametros[0] instanceof String &&    // nombre_donante
               parametros[1] instanceof BigDecimal && // monto_donacion
               parametros[2] instanceof String &&    // tipo_donacion
               parametros[3] instanceof String &&    // proyecto_especifico
               parametros[4] instanceof String &&    // reconocimiento
               parametros[5] instanceof String &&    // metodo_pago
               parametros[6] instanceof Integer &&   // id_usuario
               parametros[7] instanceof Integer;     // id_campana
    }

    @Override
    public String getDescripcionParametros() {
        return "Se requieren 8 parámetros: nombre_donante (String), monto_donacion (BigDecimal), " +
               "tipo_donacion (String), proyecto_especifico (String), reconocimiento (String), " +
               "metodo_pago (String), id_usuario (Integer), id_campana (Integer)";
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
            stmt.setString(6, (String) parametros[5]);
            stmt.setInt(7, (Integer) parametros[6]);
            stmt.setInt(8, (Integer) parametros[7]);
            stmt.execute();
            System.out.println("Donación insertada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar donación: " + e.getMessage());
        }
    }
}