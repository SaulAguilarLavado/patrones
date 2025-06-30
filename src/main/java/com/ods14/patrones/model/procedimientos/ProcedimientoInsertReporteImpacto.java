package com.ods14.patrones.model.procedimientos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.ods14.patrones.model.conexion.ConexionBD;

public class ProcedimientoInsertReporteImpacto implements ProcedimientoPrototype {
    private final String nombreProcedimiento = "sp_insert_reporte_impacto";

    @Override
    public ProcedimientoPrototype clonar() {
        return new ProcedimientoInsertReporteImpacto();
    }

    @Override
    public void ejecutar(Object... parametros) {
        if (parametros.length != 6) {
            throw new IllegalArgumentException("Se requieren 6 parámetros: id_actividad, impacto_logrado, resultados_cuantificables, residuos_recolectados, especies_monitoreadas, id_usuario");
        }
        Connection conn = ConexionBD.getInstancia().getConexion();
        try (CallableStatement stmt = conn.prepareCall("{call " + nombreProcedimiento + "(?, ?, ?, ?, ?, ?)}")) {
            stmt.setInt(1, (Integer) parametros[0]);
            stmt.setString(2, (String) parametros[1]);
            stmt.setString(3, (String) parametros[2]);
            stmt.setInt(4, (Integer) parametros[3]);
            stmt.setInt(5, (Integer) parametros[4]);
            stmt.setInt(6, (Integer) parametros[5]);
            stmt.execute();
            System.out.println("Reporte de impacto insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}