package com.ods14.patrones.observer;

public class LoggerObserver implements Observer {
    @Override
    public void actualizar(String mensaje) {
        System.out.println("[OBSERVER] " + mensaje);
    }
}