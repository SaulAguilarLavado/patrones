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
    public void ejecutar(Object... parametros) {
        if (parametros.length != 8) {
            throw new IllegalArgumentException("Se requieren 8 parámetros: nombre_donante, monto_donacion, tipo_donacion, proyecto_especifico, reconocimiento, metodo_pago, id_usuario, id_campana");
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
        }
    }
}