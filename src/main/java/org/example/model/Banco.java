package org.example.model;

public class Banco {
    private double bono;

    public Banco(double bono) {
        this.bono = bono;
    }

    public void pagarBono(Jugador jugador){
        System.out.println("¡"+jugador.getNombre()+ " has recibido $"+bono + " por dar la vuelta al tablero!");
        jugador.sumarPlata(this.bono);
    }

}
