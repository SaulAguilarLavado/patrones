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
    public void ejecutar(Object... parametros) {
        if (parametros.length != 7) {
            throw new IllegalArgumentException("Se requieren 7 parámetros: nombre_campana, objetivo_principal, publico_objetivo, mensaje_clave, duracion_dias, medios_utilizados, id_usuario_creador");
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
        }
    }
}