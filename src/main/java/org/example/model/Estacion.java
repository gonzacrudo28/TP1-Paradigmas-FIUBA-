package org.example.model;

import java.time.Period;

public class Estacion extends Propiedad {
    public Estacion(String nombre, double precio, String color, double alquiler) {
        super(nombre, precio, color, alquiler);

    }

    public void CobrarPeaje(Jugador jugador){
        if (this.estado == EstadoPropiedad.COMPRADO){
            if (jugador != this.getPropietario()){
                if (jugador.getPlata() >= this.getAlquiler()){
                    jugador.setPlata(jugador.getPlata() - this.getAlquiler());
                    

                }else{

                }
            }
        }
    }

    public void comprarEstacion(Jugador jugador){
        this.setPropietario(jugador);
    }



}
