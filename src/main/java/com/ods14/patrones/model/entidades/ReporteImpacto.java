package com.ods14.patrones.model.entidades;

public class ReporteImpacto {
    private int idReporte;
    private int idActividad;
    private String impactoLogrado;
    private String resultadosCuantificables;
    private int residuosRecolectados;
    private int especiesMonitoreadas;
    private String fechaReporte;

    // Getters y setters
    public int getIdReporte() { return idReporte; }
    public void setIdReporte(int idReporte) { this.idReporte = idReporte; }
    public int getIdActividad() { return idActividad; }
    public void setIdActividad(int idActividad) { this.idActividad = idActividad; }
    public String getImpactoLogrado() { return impactoLogrado; }
    public void setImpactoLogrado(String impactoLogrado) { this.impactoLogrado = impactoLogrado; }
    public String getResultadosCuantificables() { return resultadosCuantificables; }
    public void setResultadosCuantificables(String resultadosCuantificables) { this.resultadosCuantificables = resultadosCuantificables; }
    public int getResiduosRecolectados() { return residuosRecolectados; }
    public void setResiduosRecolectados(int residuosRecolectados) { this.residuosRecolectados = residuosRecolectados; }
    public int getEspeciesMonitoreadas() { return especiesMonitoreadas; }
    public void setEspeciesMonitoreadas(int especiesMonitoreadas) { this.especiesMonitoreadas = especiesMonitoreadas; }
    public String getFechaReporte() { return fechaReporte; }
    public void setFechaReporte(String fechaReporte) { this.fechaReporte = fechaReporte; }
}