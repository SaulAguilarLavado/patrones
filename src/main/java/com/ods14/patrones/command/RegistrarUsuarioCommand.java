package com.ods14.patrones.command;

import com.ods14.patrones.model.RegistroFacade;

public class RegistrarUsuarioCommand implements Command {
    private RegistroFacade facade;
    private String usuario;
    private String contrasena;

    public RegistrarUsuarioCommand(RegistroFacade facade, String usuario, String contrasena) {
        this.facade = facade;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    @Override
    public void ejecutar() {
        facade.registrarUsuario(usuario, contrasena);
    }
}