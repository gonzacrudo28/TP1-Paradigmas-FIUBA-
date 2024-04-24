package org.example.controller;

import org.example.model.Propiedad;

public class CheckHipotecar {
    private Jugador jugador;
    private Barrio barrio;
    private Propiedad propiedad;

    public CheckHipotecar(Jugador jugador, Barrio barrio,Propiedad propiedad){
        this.jugador = jugador;
        this.barrio = barrio;
        this.propiedad = propiedad;
    }
}
