package com.ods14.patrones.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ProcedimientoInsertEspecie implements ProcedimientoPrototype {
    private final String nombreProcedimiento = "sp_insert_especie";

    @Override
    public ProcedimientoPrototype clonar() {
        return new ProcedimientoInsertEspecie();
    }

    @Override
    public void ejecutar(Object... parametros) {
        if (parametros.length != 5) {
            throw new IllegalArgumentException("Se requieren 5 parámetros: nombre_cientifico, nombre_comun, estado, descripcion, id_ecosistema");
        }
        Connection conn = ConexionBD.getInstancia().getConexion();
        try (CallableStatement stmt = conn.prepareCall("{call " + nombreProcedimiento + "(?, ?, ?, ?, ?)}")) {
            stmt.setString(1, (String) parametros[0]);
            stmt.setString(2, (String) parametros[1]);
            stmt.setString(3, (String) parametros[2]);
            stmt.setString(4, (String) parametros[3]);
            stmt.setInt(5, (Integer) parametros[4]);
            stmt.execute();
            System.out.println("Especie insertada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}