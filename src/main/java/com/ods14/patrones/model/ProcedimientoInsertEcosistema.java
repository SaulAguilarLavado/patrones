package com.ods14.patrones.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ProcedimientoInsertEcosistema implements ProcedimientoPrototype {
    private final String nombreProcedimiento = "sp_insert_ecosistema";

    @Override
    public ProcedimientoPrototype clonar() {
        return new ProcedimientoInsertEcosistema();
    }

    @Override
    public void ejecutar(Object... parametros) {
        if (parametros.length != 1) {
            throw new IllegalArgumentException("Se requiere 1 parámetro: nombre_ecosistema");
        }
        Connection conn = ConexionBD.getInstancia().getConexion();
        try (CallableStatement stmt = conn.prepareCall("{call " + nombreProcedimiento + "(?)}")) {
            stmt.setString(1, (String) parametros[0]);
            stmt.execute();
            System.out.println("Ecosistema insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}