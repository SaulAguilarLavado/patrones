package com.ods14.patrones.controller;

import com.ods14.patrones.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/info")
public class EcosistemaInfoController {

    @GetMapping("/ecosistemas")
    public String mostrarEcosistema(Model model) {
        model.addAttribute("ecosistema", new Ecosistema());
        return "ecosistema";
    }

    @PostMapping("/ecosistemas")
    public String guardarEcosistema(@ModelAttribute Ecosistema ecosistema, Model model) {
        ProcedimientoInsertEcosistema proc = new ProcedimientoInsertEcosistema();
        proc.ejecutar(ecosistema.getNombreEcosistema());
        // Obtener el último id insertado
        int idEcosistema = Ecosistema.obtenerUltimoId();
        model.addAttribute("idEcosistema", idEcosistema);
        return "redirect:/info/especies?idEcosistema=" + idEcosistema;
    }

    @GetMapping("/especies")
    public String mostrarEspecie(@RequestParam int idEcosistema, Model model) {
        Especie especie = new Especie();
        especie.setIdEcosistema(idEcosistema);
        model.addAttribute("especie", especie);
        return "especie";
    }

    @PostMapping("/especies")
    public String guardarEspecie(@ModelAttribute Especie especie, Model model) {
        ProcedimientoInsertEspecie proc = new ProcedimientoInsertEspecie();
        proc.ejecutar(
            especie.getNombreCientifico(),
            especie.getNombreComun(),
            especie.getEstadoConservacion(),
            especie.getDescripcion(),
            especie.getIdEcosistema()
        );
        model.addAttribute("idEcosistema", especie.getIdEcosistema());
        return "redirect:/info/biodiversidad?idEcosistema=" + especie.getIdEcosistema();
    }

    @GetMapping("/biodiversidad")
    public String mostrarBiodiversidad(@RequestParam int idEcosistema, Model model) {
        Biodiversidad biodiversidad = new Biodiversidad();
        biodiversidad.setIdEcosistema(idEcosistema);
        model.addAttribute("biodiversidad", biodiversidad);
        return "biodiversidad";
    }

    @PostMapping("/biodiversidad")
    public String guardarBiodiversidad(@ModelAttribute Biodiversidad biodiversidad) {
        ProcedimientoInsertBiodiversidad proc = new ProcedimientoInsertBiodiversidad();
        proc.ejecutar(
            biodiversidad.getIdEcosistema(),
            biodiversidad.getDescripcion()
        );
        return "redirect:/info/finalizado";
    }
    
    @GetMapping("/finalizado")
    public String mostrarFinalizado() {
        return "finalizado";
    }
}