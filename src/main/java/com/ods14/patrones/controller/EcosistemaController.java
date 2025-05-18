package com.ods14.patrones.controller;

import com.ods14.patrones.model.Ecosistema;
import com.ods14.patrones.model.Usuario;
import com.ods14.patrones.model.ProcedimientoConservacion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EcosistemaController {

    @GetMapping("/ecosistemas")
    public String mostrarEcosistemas(Model model) {
        List<Ecosistema> ecosistemas = Ecosistema.obtenerTodos();
        model.addAttribute("ecosistemas", ecosistemas);
        return "ecosistemas";
    }

    @PostMapping("/ecosistemas")
    public String seleccionarEcosistema(@RequestParam String ecosistema, HttpSession session) {
        session.setAttribute("ecosistema", ecosistema);
        return "redirect:/contribucion";
    }

    @GetMapping("/contribucion")
    public String mostrarContribucion() {
        return "contribucion";
    }

    @PostMapping("/contribuir")
    public String procesarContribucion(
            @RequestParam String tipo,
            @RequestParam(required = false) String metodo_pago,
            HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            ProcedimientoConservacion proc = new ProcedimientoConservacion("sp_insert_contribucion", 3);
            proc.ejecutar(usuario.getId(), tipo, metodo_pago);
        }
        return "redirect:/finalizado"; // Redirige a una página de confirmación
    }

    @GetMapping("/finalizado")
    public String mostrarFinalizado() {
        return "finalizado";
    }
}