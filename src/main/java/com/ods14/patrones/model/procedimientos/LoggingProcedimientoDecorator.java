package com.ods14.patrones.model.procedimientos;

import java.util.Arrays;

public class LoggingProcedimientoDecorator extends ProcedimientoDecorator {
    public LoggingProcedimientoDecorator(ProcedimientoPrototype wrappee) {
        super(wrappee);
    }

    @Override
    public void ejecutar(Object... parametros) {
        System.out.println("[LOG] Ejecutando procedimiento con parámetros: " + Arrays.toString(parametros));
        super.ejecutar(parametros);
        System.out.println("[LOG] Procedimiento ejecutado.");
    }
}