package com.ods14.patrones.command;

public class Invocador {
    private Command comando;

    public void setComando(Command comando) {
        this.comando = comando;
    }

    public void ejecutarComando() {
        if (comando != null) {
            comando.ejecutar();
        }
    }
}