package com.ods14.patrones.model.procedimientos;

import java.util.ArrayList;
import java.util.List;

public class ProcedimientoComposite implements ProcedimientoPrototype {
    private List<ProcedimientoPrototype> procedimientos = new ArrayList<>();

    public void agregar(ProcedimientoPrototype proc) {
        procedimientos.add(proc);
    }

    @Override
    public ProcedimientoPrototype clonar() {
        ProcedimientoComposite copia = new ProcedimientoComposite();
        for (ProcedimientoPrototype proc : procedimientos) {
            copia.agregar(proc.clonar());
        }
        return copia;
    }

    @Override
    public void ejecutar(Object... parametros) {
        for (ProcedimientoPrototype proc : procedimientos) {
            proc.ejecutar(parametros);
        }
    }
}