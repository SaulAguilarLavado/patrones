package com.ods14.patrones.controller;

import com.ods14.patrones.model.RegistroFacade;
import com.ods14.patrones.model.entidades.Actividad;
import com.ods14.patrones.model.entidades.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

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
    public String registrarActividad(@ModelAttribute Actividad actividad) {
        facade.registrarActividad(
            actividad.getNombreActividad(),
            actividad.getFecha(),
            actividad.getHoraInicio(),
            actividad.getLugar(),
            actividad.getResponsable(),
            actividad.getDescripcion()
        );
        return "redirect:/actividad/historial";
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