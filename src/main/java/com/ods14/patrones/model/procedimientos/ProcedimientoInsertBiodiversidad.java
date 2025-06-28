package com.ods14.patrones.model.procedimientos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.ods14.patrones.model.conexion.ConexionBD;

public class ProcedimientoInsertBiodiversidad implements ProcedimientoPrototype {
    private final String nombreProcedimiento = "sp_insert_biodiversidad";

    @Override
    public ProcedimientoPrototype clonar() {
        return new ProcedimientoInsertBiodiversidad();
    }

    @Override
    public void ejecutar(Object... parametros) {
        if (parametros.length != 2) {
            throw new IllegalArgumentException("Se requieren 2 parámetros: id_ecosistema, descripcion");
        }
        Connection conn = ConexionBD.getInstancia().getConexion();
        try (CallableStatement stmt = conn.prepareCall("{call " + nombreProcedimiento + "(?, ?)}")) {
            stmt.setInt(1, (Integer) parametros[0]);
            stmt.setString(2, (String) parametros[1]);
            stmt.execute();
            System.out.println("Biodiversidad insertada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}