package com.ods14.patrones.model.dto;

public class EstadisticasImpacto {
    private int actividadesRealizadas;
    private int participacionTotales;
    private int residuosRecolectados;
    private int especiesMonitoreadas;

    // Getters y setters
    public int getActividadesRealizadas() { return actividadesRealizadas; }
    public void setActividadesRealizadas(int actividadesRealizadas) { this.actividadesRealizadas = actividadesRealizadas; }
    public int getParticipacionTotales() { return participacionTotales; }
    public void setParticipacionTotales(int participacionTotales) { this.participacionTotales = participacionTotales; }
    public int getResiduosRecolectados() { return residuosRecolectados; }
    public void setResiduosRecolectados(int residuosRecolectados) { this.residuosRecolectados = residuosRecolectados; }
    public int getEspeciesMonitoreadas() { return especiesMonitoreadas; }
    public void setEspeciesMonitoreadas(int especiesMonitoreadas) { this.especiesMonitoreadas = especiesMonitoreadas; }
}