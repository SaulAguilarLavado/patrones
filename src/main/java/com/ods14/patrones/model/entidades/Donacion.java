package com.ods14.patrones.model.entidades;

import java.math.BigDecimal;

public class Donacion {
    private Integer idDonacion;
    private String nombreDonante;
    private BigDecimal montoDonacion;
    private String tipoDonacion;
    private String proyectoEspecifico;
    private String reconocimiento;
    private String fechaDonacion;
    private String metodoPago;
    private Integer idUsuario;
    private Integer idCampana;

    // Constructor vacío
    public Donacion() {}

    // Getters y Setters
    public Integer getIdDonacion() {
        return idDonacion;
    }

    public void setIdDonacion(Integer idDonacion) {
        this.idDonacion = idDonacion;
    }

    public String getNombreDonante() {
        return nombreDonante;
    }

    public void setNombreDonante(String nombreDonante) {
        this.nombreDonante = nombreDonante;
    }

    public BigDecimal getMontoDonacion() {
        return montoDonacion;
    }

    public void setMontoDonacion(BigDecimal montoDonacion) {
        this.montoDonacion = montoDonacion;
    }

    public String getTipoDonacion() {
        return tipoDonacion;
    }

    public void setTipoDonacion(String tipoDonacion) {
        this.tipoDonacion = tipoDonacion;
    }

    public String getProyectoEspecifico() {
        return proyectoEspecifico;
    }

    public void setProyectoEspecifico(String proyectoEspecifico) {
        this.proyectoEspecifico = proyectoEspecifico;
    }

    public String getReconocimiento() {
        return reconocimiento;
    }

    public void setReconocimiento(String reconocimiento) {
        this.reconocimiento = reconocimiento;
    }

    public String getFechaDonacion() {
        return fechaDonacion;
    }

    public void setFechaDonacion(String fechaDonacion) {
        this.fechaDonacion = fechaDonacion;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdCampana() {
        return idCampana;
    }

    public void setIdCampana(Integer idCampana) {
        this.idCampana = idCampana;
    }
}