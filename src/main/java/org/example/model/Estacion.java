package org.example.model;

import java.time.Period;

public class Estacion extends Propiedad {
    public Estacion(String nombre, double precio, String color, double alquiler) {
        super(nombre, precio, color, alquiler);
    }

    public void CobrarPeaje(Jugador jugador){
        jugador.restarPlata((int)this.getPrecio());
    }

    public void comprarEstacion(Jugador jugador){
        this.setPropietario(jugador);
    }

}
