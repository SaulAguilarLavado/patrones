package com.ods14.patrones.model.entidades;

public class Campana {
    private Integer idCampana;
    private String nombreCampana;
    private String objetivoPrincipal;
    private String publicoObjetivo;
    private String mensajeClave;
    private Integer duracionDias;
    private String mediosUtilizados;
    private String estado;
    private String fechaCreacion;
    private Integer idUsuarioCreador;

    // Constructor vacío
    public Campana() {}

    // Getters y Setters
    public Integer getIdCampana() {
        return idCampana;
    }

    public void setIdCampana(Integer idCampana) {
        this.idCampana = idCampana;
    }

    public String getNombreCampana() {
        return nombreCampana;
    }

    public void setNombreCampana(String nombreCampana) {
        this.nombreCampana = nombreCampana;
    }

    public String getObjetivoPrincipal() {
        return objetivoPrincipal;
    }

    public void setObjetivoPrincipal(String objetivoPrincipal) {
        this.objetivoPrincipal = objetivoPrincipal;
    }

    public String getPublicoObjetivo() {
        return publicoObjetivo;
    }

    public void setPublicoObjetivo(String publicoObjetivo) {
        this.publicoObjetivo = publicoObjetivo;
    }

    public String getMensajeClave() {
        return mensajeClave;
    }

    public void setMensajeClave(String mensajeClave) {
        this.mensajeClave = mensajeClave;
    }

    public Integer getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(Integer duracionDias) {
        this.duracionDias = duracionDias;
    }

    public String getMediosUtilizados() {
        return mediosUtilizados;
    }

    public void setMediosUtilizados(String mediosUtilizados) {
        this.mediosUtilizados = mediosUtilizados;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdUsuarioCreador() {
        return idUsuarioCreador;
    }

    public void setIdUsuarioCreador(Integer idUsuarioCreador) {
        this.idUsuarioCreador = idUsuarioCreador;
    }
}