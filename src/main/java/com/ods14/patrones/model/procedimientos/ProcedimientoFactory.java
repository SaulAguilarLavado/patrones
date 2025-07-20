package com.ods14.patrones.model.procedimientos;

import java.util.HashMap;
import java.util.Map;

public class ProcedimientoFactory {
    
    private static final Map<String, String> PROCEDIMIENTOS_INFO = new HashMap<>();
    
    static {
        PROCEDIMIENTOS_INFO.put("usuario", "Insertar usuario - 2 parámetros");
        PROCEDIMIENTOS_INFO.put("campana", "Insertar campaña - 7 parámetros");
        PROCEDIMIENTOS_INFO.put("donacion", "Insertar donación - 8 parámetros");
        PROCEDIMIENTOS_INFO.put("evento_recaudacion", "Insertar evento - 8 parámetros");
        PROCEDIMIENTOS_INFO.put("actividad", "Insertar actividad - 6 parámetros");
        PROCEDIMIENTOS_INFO.put("ecosistema", "Insertar ecosistema - 1 parámetro");
        PROCEDIMIENTOS_INFO.put("especie", "Insertar especie - 5 parámetros");
        PROCEDIMIENTOS_INFO.put("biodiversidad", "Insertar biodiversidad - 2 parámetros");
        PROCEDIMIENTOS_INFO.put("participante_actividad", "Insertar participante - 2 parámetros");
        PROCEDIMIENTOS_INFO.put("reporte_impacto", "Insertar reporte - 6 parámetros");
    }
    
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
            case "campana":
                return new ProcedimientoInsertCampana();
            case "donacion":
                return new ProcedimientoInsertDonacion();
            case "evento_recaudacion":
                return new ProcedimientoInsertEventoRecaudacion();
            default:
                throw new IllegalArgumentException("Tipo de procedimiento no soportado: " + tipo + 
                    ". Tipos disponibles: " + PROCEDIMIENTOS_INFO.keySet());
        }
    }
    
    // ✅ MEJORA: Obtener información de procedimientos
    public static String getInfo(String tipo) {
        return PROCEDIMIENTOS_INFO.getOrDefault(tipo, "Procedimiento desconocido");
    }
    
    public static Map<String, String> getTodosLosProcedimientos() {
        return new HashMap<>(PROCEDIMIENTOS_INFO);
    }
}