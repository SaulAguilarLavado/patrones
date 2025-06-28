package com.ods14.patrones.model.procedimientos;

public interface ProcedimientoPrototype extends Cloneable {
    ProcedimientoPrototype clonar();
    void ejecutar(Object... parametros);
}