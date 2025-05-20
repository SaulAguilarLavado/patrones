package com.ods14.patrones.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Usuario {
    private int id;
    private String nombre;
    private String contrasena;

    public Usuario(int id, String nombre, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getContrasena() { return contrasena; }

    public static Usuario buscarPorNombre(String nombreUsuario) {
        try {
            Connection conn = ConexionBD.getInstancia().getConexion();
            PreparedStatement stmt = conn.prepareStatement("CALL sp_get_usuario(?)");
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre_usuario"),
                    rs.getString("contrasena")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}