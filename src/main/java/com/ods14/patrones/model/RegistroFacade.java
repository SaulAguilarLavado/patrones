package com.ods14.patrones.model;

import com.ods14.patrones.model.procedimientos.ProcedimientoInsertBiodiversidad;
import com.ods14.patrones.model.procedimientos.ProcedimientoInsertEcosistema;
import com.ods14.patrones.model.procedimientos.ProcedimientoInsertEspecie;
import com.ods14.patrones.model.procedimientos.ProcedimientoInsertUsuario;

public class RegistroFacade {
    public void registrarUsuario(String usuario, String contrasena) {
        ProcedimientoInsertUsuario proc = new ProcedimientoInsertUsuario();
        proc.ejecutar(usuario, contrasena);
    }

    public void registrarEcosistema(String nombreEcosistema) {
        ProcedimientoInsertEcosistema proc = new ProcedimientoInsertEcosistema();
        proc.ejecutar(nombreEcosistema);
    }

    public void registrarEspecie(String nombreCientifico, String nombreComun, String estado, String descripcion, int idEcosistema) {
        ProcedimientoInsertEspecie proc = new ProcedimientoInsertEspecie();
        proc.ejecutar(nombreCientifico, nombreComun, estado, descripcion, idEcosistema);
    }

    public void registrarBiodiversidad(int idEcosistema, String descripcion) {
        ProcedimientoInsertBiodiversidad proc = new ProcedimientoInsertBiodiversidad();
        proc.ejecutar(idEcosistema, descripcion);
    }
}
