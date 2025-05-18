package com.ods14.patrones.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Ecosistema {
    private int id;
    private String nombre;

    public Ecosistema(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }

    public static List<Ecosistema> obtenerTodos() {
        List<Ecosistema> lista = new ArrayList<>();
        try {
            Connection conn = ConexionBD.getInstancia().getConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ecosistema");
            while (rs.next()) {
                lista.add(new Ecosistema(rs.getInt("id_ecosistema"), rs.getString("nombre_ecosistema")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
