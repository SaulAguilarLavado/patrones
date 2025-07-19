package com.ods14.patrones.model.entidades;

import java.math.BigDecimal;

public class EventoRecaudacion {
    private Integer idEvento;
    private String nombreEvento;
    private BigDecimal metaRecaudacion;
    private String fechaEvento;
    private String lugarEvento;
    private String tipoEvento;
    private BigDecimal gastosEstimados;
    private String estado;
    private Integer idUsuarioOrganizador;
    private Integer idCampana;

    // Constructor vacío
    public EventoRecaudacion() {}

    // Getters y Setters
    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public BigDecimal getMetaRecaudacion() {
        return metaRecaudacion;
    }

    public void setMetaRecaudacion(BigDecimal metaRecaudacion) {
        this.metaRecaudacion = metaRecaudacion;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getLugarEvento() {
        return lugarEvento;
    }

    public void setLugarEvento(String lugarEvento) {
        this.lugarEvento = lugarEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public BigDecimal getGastosEstimados() {
        return gastosEstimados;
    }

    public void setGastosEstimados(BigDecimal gastosEstimados) {
        this.gastosEstimados = gastosEstimados;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdUsuarioOrganizador() {
        return idUsuarioOrganizador;
    }

    public void setIdUsuarioOrganizador(Integer idUsuarioOrganizador) {
        this.idUsuarioOrganizador = idUsuarioOrganizador;
    }

    public Integer getIdCampana() {
        return idCampana;
    }

    public void setIdCampana(Integer idCampana) {
        this.idCampana = idCampana;
    }
}