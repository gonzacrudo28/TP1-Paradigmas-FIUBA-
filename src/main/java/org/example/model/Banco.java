package org.example.model;

public class Banco {

    private double bono;

    public Banco(double bono) {
        this.bono = bono;
    }

    public void setBono(int bono){
        this.bono = bono;
    }

    public double getBono(){
        return bono;
    }

    public void pagarBono(Jugador jugador){
        jugador.sumarPlata(this.bono);
    }
}
