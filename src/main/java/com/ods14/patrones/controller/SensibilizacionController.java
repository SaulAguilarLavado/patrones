package com.ods14.patrones.controller;

import com.ods14.patrones.model.RegistroFacade;
import com.ods14.patrones.model.dto.EstadisticasDonaciones;
import com.ods14.patrones.model.entidades.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/sensibilizacion")
public class SensibilizacionController {

    private final RegistroFacade facade = new RegistroFacade();

    // 1. CREAR CAMPAÑA
    @GetMapping("/campana")
    public String mostrarCrearCampana(Model model) {
        model.addAttribute("campana", new Campana());
        return "crear_campana";
    }

    @PostMapping("/campana")
    public String crearCampana(@ModelAttribute Campana campana, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        facade.registrarCampana(
            campana.getNombreCampana(),
            campana.getObjetivoPrincipal(),
            campana.getPublicoObjetivo(),
            campana.getMensajeClave(),
            campana.getDuracionDias(),
            campana.getMediosUtilizados(),
            usuario.getId()
        );
        return "redirect:/sensibilizacion/donacion";
    }

    // 2. GESTIONAR DONACIONES
    @GetMapping("/donacion")
    public String mostrarGestionarDonacion(Model model) {
        model.addAttribute("donacion", new Donacion());
        List<Campana> campanasActivas = facade.obtenerCampanasActivas();
        model.addAttribute("campanas", campanasActivas);
        return "gestionar_donacion";
    }

    @PostMapping("/donacion")
    public String gestionarDonacion(@ModelAttribute Donacion donacion, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        facade.registrarDonacion(
            donacion.getNombreDonante(),
            donacion.getMontoDonacion(),
            donacion.getTipoDonacion(),
            donacion.getProyectoEspecifico(),
            donacion.getReconocimiento(),
            donacion.getMetodoPago(),
            usuario.getId(),
            donacion.getIdCampana()
        );
        return "redirect:/sensibilizacion/evento";
    }

    // 3. RECOLECCIÓN DE FONDOS
    @GetMapping("/evento")
    public String mostrarRecoleccionFondos(Model model) {
        model.addAttribute("evento", new EventoRecaudacion());
        List<Campana> campanasActivas = facade.obtenerCampanasActivas();
        model.addAttribute("campanas", campanasActivas);
        return "recoleccion_fondos";
    }

    @PostMapping("/evento")
    public String crearEventoRecaudacion(@ModelAttribute EventoRecaudacion evento, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        facade.registrarEventoRecaudacion(
            evento.getNombreEvento(),
            evento.getMetaRecaudacion(),
            evento.getFechaEvento(),
            evento.getLugarEvento(),
            evento.getTipoEvento(),
            evento.getGastosEstimados(),
            usuario.getId(),
            evento.getIdCampana()
        );
        return "redirect:/sensibilizacion/estadisticas";
    }

    // 4. ESTADÍSTICAS DE DONACIONES
    @GetMapping("/estadisticas")
    public String mostrarEstadisticas(Model model) {
        EstadisticasDonaciones estadisticas = facade.obtenerEstadisticasDonaciones();
        model.addAttribute("estadisticas", estadisticas);
        return "estadisticas_donaciones";
    }
}