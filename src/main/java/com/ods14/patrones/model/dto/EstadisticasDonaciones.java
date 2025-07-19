package com.ods14.patrones.model.dto;

import java.math.BigDecimal;

public class EstadisticasDonaciones {
    private BigDecimal totalRecaudado;
    private Integer numeroDonantes;
    private Integer campanasActivas;
    private Integer eventosRealizados;
    private Integer proyectosFinanciados;

    // Constructor vacío
    public EstadisticasDonaciones() {}

    // Getters y Setters
    public BigDecimal getTotalRecaudado() {
        return totalRecaudado;
    }

    public void setTotalRecaudado(BigDecimal totalRecaudado) {
        this.totalRecaudado = totalRecaudado;
    }

    public Integer getNumeroDonantes() {
        return numeroDonantes;
    }

    public void setNumeroDonantes(Integer numeroDonantes) {
        this.numeroDonantes = numeroDonantes;
    }

    public Integer getCampanasActivas() {
        return campanasActivas;
    }

    public void setCampanasActivas(Integer campanasActivas) {
        this.campanasActivas = campanasActivas;
    }

    public Integer getEventosRealizados() {
        return eventosRealizados;
    }

    public void setEventosRealizados(Integer eventosRealizados) {
        this.eventosRealizados = eventosRealizados;
    }

    public Integer getProyectosFinanciados() {
        return proyectosFinanciados;
    }

    public void setProyectosFinanciados(Integer proyectosFinanciados) {
        this.proyectosFinanciados = proyectosFinanciados;
    }
}