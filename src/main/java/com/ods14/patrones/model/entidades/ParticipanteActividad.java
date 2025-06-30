package com.ods14.patrones.model.entidades;

public class ParticipanteActividad {
    private int idParticipante;
    private int idActividad;
    private String nombreParticipante;

    // Getters y setters
    public int getIdParticipante() { return idParticipante; }
    public void setIdParticipante(int idParticipante) { this.idParticipante = idParticipante; }
    public int getIdActividad() { return idActividad; }
    public void setIdActividad(int idActividad) { this.idActividad = idActividad; }
    public String getNombreParticipante() { return nombreParticipante; }
    public void setNombreParticipante(String nombreParticipante) { this.nombreParticipante = nombreParticipante; }
}