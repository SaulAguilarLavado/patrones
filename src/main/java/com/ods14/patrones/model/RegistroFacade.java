package com.ods14.patrones.model;

import com.ods14.patrones.model.procedimientos.ProcedimientoFactory;
import com.ods14.patrones.model.procedimientos.ProcedimientoPrototype;

public class RegistroFacade {
    public void registrarUsuario(String usuario, String contrasena) {
        ProcedimientoPrototype proc = ProcedimientoFactory.crear("usuario");
        proc.ejecutar(usuario, contrasena);
    }

    public void registrarEcosistema(String nombreEcosistema) {
        ProcedimientoPrototype proc = ProcedimientoFactory.crear("ecosistema");
        proc.ejecutar(nombreEcosistema);
    }

    public void registrarEspecie(String nombreCientifico, String nombreComun, String estado, String descripcion, int idEcosistema) {
        ProcedimientoPrototype proc = ProcedimientoFactory.crear("especie");
        proc.ejecutar(nombreCientifico, nombreComun, estado, descripcion, idEcosistema);
    }

    public void registrarBiodiversidad(int idEcosistema, String descripcion) {
        ProcedimientoPrototype proc = ProcedimientoFactory.crear("biodiversidad");
        proc.ejecutar(idEcosistema, descripcion);
    }
}