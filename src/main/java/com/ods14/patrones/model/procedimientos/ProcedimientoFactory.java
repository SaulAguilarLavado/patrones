package com.ods14.patrones.model.procedimientos;

public class ProcedimientoFactory {
    public static ProcedimientoPrototype crear(String tipo) {
        switch (tipo) {
            case "usuario":
                return new ProcedimientoInsertUsuario();
            case "ecosistema":
                return new ProcedimientoInsertEcosistema();
            case "especie":
                return new ProcedimientoInsertEspecie();
            case "biodiversidad":
                return new ProcedimientoInsertBiodiversidad();
            case "actividad":
                return new ProcedimientoInsertActividad();
            case "participante_actividad":
                return new ProcedimientoInsertParticipanteActividad();
            case "reporte_impacto":
                return new ProcedimientoInsertReporteImpacto();
            // NUEVOS PROCEDIMIENTOS
            case "campana":
                return new ProcedimientoInsertCampana();
            case "donacion":
                return new ProcedimientoInsertDonacion();
            case "evento_recaudacion":
                return new ProcedimientoInsertEventoRecaudacion();
            default:
                throw new IllegalArgumentException("Tipo de procedimiento no soportado: " + tipo);
        }
    }
}