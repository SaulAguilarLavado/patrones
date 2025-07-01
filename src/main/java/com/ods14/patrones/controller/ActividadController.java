package com.ods14.patrones.controller;

import com.ods14.patrones.model.RegistroFacade;
import com.ods14.patrones.model.entidades.Actividad;
import com.ods14.patrones.model.entidades.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/actividad")
public class ActividadController {

    private final RegistroFacade facade = new RegistroFacade();

    @GetMapping("/registrar")
    public String mostrarRegistroActividad(Model model) {
        model.addAttribute("actividad", new Actividad());
        return "registro_actividad";
    }

    @PostMapping("/registrar")
    public String registrarActividad(@ModelAttribute Actividad actividad, HttpSession session) {
        // Depuración: imprime el responsable recibido
        System.out.println("Responsable recibido: " + actividad.getResponsable());

        // 1. Registrar la actividad
        facade.registrarActividad(
                actividad.getNombreActividad(),
                actividad.getFecha(),
                actividad.getHoraInicio(),
                actividad.getLugar(),
                actividad.getResponsable(),
                actividad.getDescripcion());

        // 2. Obtener el usuario autenticado
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            // 3. Obtener el último id de actividad registrada
            int idUltimaActividad = obtenerUltimoIdActividad();
            // 4. Registrar al usuario como participante
            facade.registrarParticipanteActividad(idUltimaActividad, usuario.getNombre());
        }

        return "redirect:/actividad/historial";
    }

    // Método auxiliar para obtener el último id de actividad registrada
    private int obtenerUltimoIdActividad() {
        int id = -1;
        try (Connection conn = com.ods14.patrones.model.conexion.ConexionBD.getInstancia().getConexion();
                PreparedStatement stmt = conn.prepareStatement("SELECT MAX(id_actividad) AS id FROM actividad");
                ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @GetMapping("/historial")
    public String historialActividades(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String nombreUsuario = (usuario != null) ? usuario.getNombre() : "Invitado";
        List<Actividad> historial = new ArrayList<>();
        if (usuario != null) {
            historial = facade.obtenerHistorialActividadesPorUsuario(usuario.getId());
        }
        model.addAttribute("historial", historial);
        model.addAttribute("nombreUsuario", nombreUsuario);
        return "historial_actividades";
    }
}