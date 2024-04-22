package org.example.model;

public class Banco {

    private int bono;

    public Banco(int bono) {
        this.bono = bono;
    }

    public void setBono(int bono){
        this.bono = bono;
    }

    public int getBono(){
        return bono;
    }

    public void pagarBono(Jugador jugador){
        jugador.sumarPlata(this.bono);
    }
}
