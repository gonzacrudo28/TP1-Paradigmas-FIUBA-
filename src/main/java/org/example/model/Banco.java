package org.example.model;

import org.example.funciones.FuncionesExtras;

public class Banco {
    private double bono;

    public Banco(double bono) {
        this.bono = bono;
    }

    public String pagarBono(Jugador jugador){
        //System.out.println("¡"+jugador.getNombre()+ " has recibido $"+bono + " por dar la vuelta al tablero!");
        FuncionesExtras.delay(1500);
        jugador.sumarPlata(this.bono);
        return "¡"+jugador.getNombre()+ " has recibido $"+bono + " por dar la vuelta al tablero!";
    }

    public enum EstadoAcciones {
        SIN_PROPIEADES,CON_CASA,CON_BARRIO,PRESO
    }
}
