package com.ods14.patrones.controller;

import com.ods14.patrones.model.RegistroFacade;
import com.ods14.patrones.model.dto.EstadisticasImpacto;
import com.ods14.patrones.model.entidades.ReporteImpacto;
import com.ods14.patrones.model.entidades.Usuario;
import com.ods14.patrones.model.entidades.Actividad;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/impacto")
public class ReporteImpactoController {

    private final RegistroFacade facade = new RegistroFacade();

    @GetMapping("/registrar")
    public String mostrarRegistroReporte(Model model, HttpSession session) {
        model.addAttribute("reporte", new ReporteImpacto());
        // Obtener usuario autenticado
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Actividad> actividades = new ArrayList<>();
        if (usuario != null) {
            actividades = facade.obtenerHistorialActividadesPorUsuario(usuario.getId());
        }
        model.addAttribute("actividades", actividades);
        return "reporte_impacto";
    }

    @PostMapping("/registrar")
    public String registrarReporte(@ModelAttribute ReporteImpacto reporte, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        int idUsuario = usuario.getId();
        // Registra el reporte de impacto con el usuario actual
        facade.registrarReporteImpacto(
            reporte.getIdActividad(),
            reporte.getImpactoLogrado(),
            reporte.getResultadosCuantificables(),
            reporte.getResiduosRecolectados(),
            reporte.getEspeciesMonitoreadas(),
            idUsuario
        );
        return "redirect:/impacto/estadisticas";
    }

    @GetMapping("/estadisticas")
    public String mostrarEstadisticas(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        int idUsuario = usuario.getId();
        EstadisticasImpacto estadisticas = facade.obtenerEstadisticasImpactoPorUsuario(idUsuario);
        model.addAttribute("estadisticas", estadisticas);
        return "estadisticas_impacto";
    }
}