package com.ods14.patrones.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ecosistema {
    private int idEcosistema;
    private String nombreEcosistema;

    // Getters y setters
    public int getIdEcosistema() { return idEcosistema; }
    public void setIdEcosistema(int idEcosistema) { this.idEcosistema = idEcosistema; }
    public String getNombreEcosistema() { return nombreEcosistema; }
    public void setNombreEcosistema(String nombreEcosistema) { this.nombreEcosistema = nombreEcosistema; }

    // MÉTODO ESTÁTICO PARA OBTENER EL ÚLTIMO ID
    public static int obtenerUltimoId() {
        try {
            Connection conn = ConexionBD.getInstancia().getConexion();
            PreparedStatement stmt = conn.prepareStatement("SELECT MAX(id_ecosistema) AS id FROM ecosistema");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}