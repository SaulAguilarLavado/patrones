package com.ods14.patrones.observer;

import java.util.ArrayList;
import java.util.List;

public class RegistroObservable {
    private List<Observer> observadores = new ArrayList<>();

    public void agregarObservador(Observer o) {
        observadores.add(o);
    }

    public void notificar(String mensaje) {
        for (Observer o : observadores) {
            o.actualizar(mensaje);
        }
    }
}