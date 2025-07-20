package com.ods14.patrones.model.procedimientos;

public interface ProcedimientoPrototype extends Cloneable {
    ProcedimientoPrototype clonar();
    void ejecutar(Object... parametros);
    
    default boolean validarParametros(Object... parametros) {
        return true; // Por defecto acepta cualquier parámetro
    }
    
    default String getTipo() {
        return this.getClass().getSimpleName();
    }
    
    default String getDescripcionParametros() {
        return "Parámetros variables";
    }
}