package com.ods14.patrones.model;

import com.ods14.patrones.model.procedimientos.ProcedimientoFactory;
import com.ods14.patrones.model.procedimientos.ProcedimientoPrototype;
import com.ods14.patrones.model.procedimientos.ProcedimientoProxy;
import com.ods14.patrones.observer.RegistroObservable;
import com.ods14.patrones.observer.LoggerObserver;
import com.ods14.patrones.model.entidades.Actividad;
import com.ods14.patrones.model.conexion.ConexionBD;
import com.ods14.patrones.model.dto.EstadisticasImpacto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    
    public void registrarActividad(String nombreActividad, String fecha, String horaInicio, String lugar, String responsable, String descripcion) {
        ProcedimientoPrototype proc = new ProcedimientoProxy(
            ProcedimientoFactory.crear("actividad")
        );
        proc.ejecutar(nombreActividad, fecha, horaInicio, lugar, responsable, descripcion);
        observable.notificar("Actividad registrada: " + nombreActividad);
    }
    
    public void registrarParticipanteActividad(int idActividad, String nombreParticipante) {
        ProcedimientoPrototype proc = new ProcedimientoProxy(
            ProcedimientoFactory.crear("participante_actividad")
        );
        proc.ejecutar(idActividad, nombreParticipante);
        observable.notificar("Participante registrado en actividad: " + idActividad);
    }
    
    // Cambiado: ahora incluye idUsuario
    public void registrarReporteImpacto(int idActividad, String impactoLogrado, String resultadosCuantificables, int residuosRecolectados, int especiesMonitoreadas, int idUsuario) {
        ProcedimientoPrototype proc = new ProcedimientoProxy(
            ProcedimientoFactory.crear("reporte_impacto")
        );
        proc.ejecutar(idActividad, impactoLogrado, resultadosCuantificables, residuosRecolectados, especiesMonitoreadas, idUsuario);
        observable.notificar("Reporte de impacto registrado para actividad: " + idActividad + " por usuario: " + idUsuario);
    }

    // NUEVO: historial de actividades solo para el usuario autenticado
    public List<Actividad> obtenerHistorialActividadesPorUsuario(int idUsuario) {
        List<Actividad> actividades = new ArrayList<>();
        String sql = "SELECT a.id_actividad, a.nombre_actividad, a.fecha, a.hora_inicio, a.lugar, a.responsable, a.descripcion " +
                     "FROM actividad a " +
                     "JOIN participante_actividad p ON a.id_actividad = p.id_actividad " +
                     "WHERE p.nombre_participante = (SELECT nombre_usuario FROM usuario WHERE id_usuario = ?)";
        try (Connection conn = ConexionBD.getInstancia().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Actividad act = new Actividad();
                    act.setIdActividad(rs.getInt("id_actividad"));
                    act.setNombreActividad(rs.getString("nombre_actividad"));
                    act.setFecha(rs.getString("fecha"));
                    act.setHoraInicio(rs.getString("hora_inicio"));
                    act.setLugar(rs.getString("lugar"));
                    act.setResponsable(rs.getString("responsable"));
                    act.setDescripcion(rs.getString("descripcion"));
                    actividades.add(act);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actividades;
    }

    // Método original (todas las actividades, no filtra por usuario)
    public List<Actividad> obtenerHistorialActividades() {
        List<Actividad> actividades = new ArrayList<>();
        String sql = "SELECT id_actividad, nombre_actividad, fecha, hora_inicio, lugar, responsable, descripcion FROM actividad";
        try (Connection conn = ConexionBD.getInstancia().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Actividad act = new Actividad();
                act.setIdActividad(rs.getInt("id_actividad"));
                act.setNombreActividad(rs.getString("nombre_actividad"));
                act.setFecha(rs.getString("fecha"));
                act.setHoraInicio(rs.getString("hora_inicio"));
                act.setLugar(rs.getString("lugar"));
                act.setResponsable(rs.getString("responsable"));
                act.setDescripcion(rs.getString("descripcion"));
                actividades.add(act);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actividades;
    }

    // Estadísticas por usuario
    public EstadisticasImpacto obtenerEstadisticasImpactoPorUsuario(int idUsuario) {
        EstadisticasImpacto est = new EstadisticasImpacto();

        try (Connection conn = ConexionBD.getInstancia().getConexion()) {
            // Actividades realizadas por el usuario (distintas actividades en las que hizo reporte)
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT COUNT(DISTINCT id_actividad) FROM reporte_impacto WHERE id_usuario = ?")) {
                stmt.setInt(1, idUsuario);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) est.setActividadesRealizadas(rs.getInt(1));
            }
            // Participaciones totales (cantidad de reportes hechos por el usuario)
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT COUNT(*) FROM reporte_impacto WHERE id_usuario = ?")) {
                stmt.setInt(1, idUsuario);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) est.setParticipacionTotales(rs.getInt(1));
            }
            // Residuos recolectados y especies monitoreadas por el usuario
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT COALESCE(SUM(residuos_recolectados),0), COALESCE(SUM(especies_monitoreadas),0) FROM reporte_impacto WHERE id_usuario = ?")) {
                stmt.setInt(1, idUsuario);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    est.setResiduosRecolectados(rs.getInt(1));
                    est.setEspeciesMonitoreadas(rs.getInt(2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return est;
    }
}