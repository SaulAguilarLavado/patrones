package com.ods14.patrones.model.entidades;

public class Biodiversidad {
    private int idBiodiversidad;
    private int idEcosistema;
    private String descripcion;
    
    public int getIdBiodiversidad() {
        return idBiodiversidad;
    }
    public void setIdBiodiversidad(int idBiodiversidad) {
        this.idBiodiversidad = idBiodiversidad;
    }
    
    public int getIdEcosistema() {
        return idEcosistema;
    }
    public void setIdEcosistema(int idEcosistema) {
        this.idEcosistema = idEcosistema;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
}
