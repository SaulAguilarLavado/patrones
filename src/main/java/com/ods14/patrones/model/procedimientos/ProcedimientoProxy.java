package com.ods14.patrones.model.procedimientos;

public class ProcedimientoProxy implements ProcedimientoPrototype {
    private ProcedimientoPrototype realProcedimiento;

    public ProcedimientoProxy(ProcedimientoPrototype realProcedimiento) {
        this.realProcedimiento = realProcedimiento;
    }

    @Override
    public ProcedimientoPrototype clonar() {
        return new ProcedimientoProxy(realProcedimiento.clonar());
    }

    @Override
    public void ejecutar(Object... parametros) {
        // Ejemplo: control de acceso simple
        System.out.println("[PROXY] Verificando acceso...");
        if (permitido()) {
            System.out.println("[PROXY] Acceso permitido. Ejecutando procedimiento real...");
            realProcedimiento.ejecutar(parametros);
        } else {
            System.out.println("[PROXY] Acceso denegado al procedimiento.");
        }
    }

    private boolean permitido() {
        return true; // Por ahora, siempre permite
    }
}