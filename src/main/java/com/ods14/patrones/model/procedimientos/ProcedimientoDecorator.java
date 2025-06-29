package com.ods14.patrones.model.procedimientos;

public abstract class ProcedimientoDecorator implements ProcedimientoPrototype {
    protected ProcedimientoPrototype wrappee;

    public ProcedimientoDecorator(ProcedimientoPrototype wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public ProcedimientoPrototype clonar() {
        return wrappee.clonar();
    }

    @Override
    public void ejecutar(Object... parametros) {
        wrappee.ejecutar(parametros);
    }
}