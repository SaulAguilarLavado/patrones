package com.ods14.patrones.model.entidades;

public class Especie {
    private int idEspecie;
    private String nombreCientifico;
    private String nombreComun;
    private String estadoConservacion;
    private String descripcion;
    private int idEcosistema;

    // Getters y Setters
    public int getIdEspecie() {
        return idEspecie;
    }
    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }
    public String getNombreCientifico() {
        return nombreCientifico;
    }
    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }
    public String getNombreComun() {
        return nombreComun;
    }
    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }
    public String getEstadoConservacion() {
        return estadoConservacion;
    }
    public void setEstadoConservacion(String estadoConservacion) {
        this.estadoConservacion = estadoConservacion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getIdEcosistema() {
        return idEcosistema;
    }
    public void setIdEcosistema(int idEcosistema) {
        this.idEcosistema = idEcosistema;
    }

}
