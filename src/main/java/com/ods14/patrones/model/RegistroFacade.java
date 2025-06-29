package com.ods14.patrones.model;

import com.ods14.patrones.model.procedimientos.ProcedimientoFactory;
import com.ods14.patrones.model.procedimientos.ProcedimientoPrototype;
import com.ods14.patrones.model.procedimientos.ProcedimientoProxy;
import com.ods14.patrones.observer.RegistroObservable;
import com.ods14.patrones.observer.LoggerObserver;

public class RegistroFacade {
    private static final RegistroObservable observable = new RegistroObservable();
    static {
        observable.agregarObservador(new LoggerObserver());
    }

    public void registrarUsuario(String usuario, String contrasena) {
        ProcedimientoPrototype proc = new ProcedimientoProxy(
            ProcedimientoFactory.crear("usuario")
        );
        proc.ejecutar(usuario, contrasena);
        observable.notificar("Usuario registrado: " + usuario);
    }

    public void registrarEcosistema(String nombreEcosistema) {
        ProcedimientoPrototype proc = new ProcedimientoProxy(
            ProcedimientoFactory.crear("ecosistema")
        );
        proc.ejecutar(nombreEcosistema);
        observable.notificar("Ecosistema registrado: " + nombreEcosistema);
    }

    public void registrarEspecie(String nombreCientifico, String nombreComun, String estado, String descripcion, int idEcosistema) {
        ProcedimientoPrototype proc = new ProcedimientoProxy(
            ProcedimientoFactory.crear("especie")
        );
        proc.ejecutar(nombreCientifico, nombreComun, estado, descripcion, idEcosistema);
        observable.notificar("Especie registrada: " + nombreCientifico);
    }

    public void registrarBiodiversidad(int idEcosistema, String descripcion) {
        ProcedimientoPrototype proc = new ProcedimientoProxy(
            ProcedimientoFactory.crear("biodiversidad")
        );
        proc.ejecutar(idEcosistema, descripcion);
        observable.notificar("Biodiversidad registrada en ecosistema: " + idEcosistema);
    }
}