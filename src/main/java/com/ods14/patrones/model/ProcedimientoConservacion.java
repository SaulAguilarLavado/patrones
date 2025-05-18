package com.ods14.patrones.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ProcedimientoConservacion implements ProcedimientoPrototype {
    private String nombreProcedimiento;
    private int cantidadParametros;

    public ProcedimientoConservacion(String nombreProcedimiento, int cantidadParametros) {
        this.nombreProcedimiento = nombreProcedimiento;
        this.cantidadParametros = cantidadParametros;
    }

    @Override
    public ProcedimientoPrototype clonar() {
        return new ProcedimientoConservacion(this.nombreProcedimiento, this.cantidadParametros);
    }

    @Override
    public void ejecutar(Object... parametros) {
        if (parametros.length != cantidadParametros) {
            throw new IllegalArgumentException("Cantidad de parámetros incorrecta");
        }
        Connection conn = ConexionBD.getInstancia().getConexion();
        StringBuilder sb = new StringBuilder("{call " + nombreProcedimiento + "(");
        for (int i = 0; i < cantidadParametros; i++) {
            sb.append("?");
            if (i < cantidadParametros - 1) sb.append(", ");
        }
        sb.append(")}");
        try (CallableStatement stmt = conn.prepareCall(sb.toString())) {
            for (int i = 0; i < cantidadParametros; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }
            stmt.execute();
            System.out.println("Procedimiento " + nombreProcedimiento + " ejecutado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
