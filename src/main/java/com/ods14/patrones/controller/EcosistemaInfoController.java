package com.ods14.patrones.controller;


import com.ods14.patrones.model.RegistroFacade;
import com.ods14.patrones.model.entidades.Biodiversidad;
import com.ods14.patrones.model.entidades.Ecosistema;
import com.ods14.patrones.model.entidades.Especie;

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

    @GetMapping("/especies")
    public String mostrarEspecie(@RequestParam int idEcosistema, Model model) {
        Especie especie = new Especie();
        especie.setIdEcosistema(idEcosistema);
        model.addAttribute("especie", especie);
        return "especie";
    }

    @GetMapping("/biodiversidad")
    public String mostrarBiodiversidad(@RequestParam int idEcosistema, Model model) {
        Biodiversidad biodiversidad = new Biodiversidad();
        biodiversidad.setIdEcosistema(idEcosistema);
        model.addAttribute("biodiversidad", biodiversidad);
        return "biodiversidad";
    }
    
    @GetMapping("/finalizado")
    public String mostrarFinalizado() {
        return "finalizado";
    }

    private final RegistroFacade facade = new RegistroFacade();

    @PostMapping("/ecosistemas")
    public String guardarEcosistema(@ModelAttribute Ecosistema ecosistema, Model model) {
        facade.registrarEcosistema(ecosistema.getNombreEcosistema());
        int idEcosistema = Ecosistema.obtenerUltimoId();
        model.addAttribute("idEcosistema", idEcosistema);
        return "redirect:/info/especies?idEcosistema=" + idEcosistema;
    }

    @PostMapping("/especies")
    public String guardarEspecie(@ModelAttribute Especie especie, Model model) {
        facade.registrarEspecie(
            especie.getNombreCientifico(),
            especie.getNombreComun(),
            especie.getEstadoConservacion(),
            especie.getDescripcion(),
            especie.getIdEcosistema()
        );
        model.addAttribute("idEcosistema", especie.getIdEcosistema());
        return "redirect:/info/biodiversidad?idEcosistema=" + especie.getIdEcosistema();
    }

    @PostMapping("/biodiversidad")
    public String guardarBiodiversidad(@ModelAttribute Biodiversidad biodiversidad) {
        facade.registrarBiodiversidad(
            biodiversidad.getIdEcosistema(),
            biodiversidad.getDescripcion()
        );
        return "redirect:/info/finalizado";
    }
}