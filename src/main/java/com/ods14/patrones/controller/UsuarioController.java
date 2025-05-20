package com.ods14.patrones.controller;

import com.ods14.patrones.model.ProcedimientoInsertUsuario;
import com.ods14.patrones.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }
    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String usuario, @RequestParam String contrasena, Model model,
            HttpSession session) {
        Usuario user = Usuario.buscarPorNombre(usuario);
        if (user != null && user.getContrasena().equals(contrasena)) {
            session.setAttribute("usuario", user);
            return "redirect:/info/ecosistemas";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/signup")
    public String mostrarSignup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String usuario, @RequestParam String contrasena, Model model) {
        try {
            ProcedimientoInsertUsuario proc = new ProcedimientoInsertUsuario();
            proc.ejecutar(usuario, contrasena);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "El usuario ya existe o hubo un error.");
            return "signup";
        }
    }
}