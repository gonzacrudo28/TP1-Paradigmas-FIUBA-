package org.example.model.tipoCasilleros;
public class Carcel extends Casillero{
    private int fianza;
    private int cantTurnos;
    public Carcel(int ubicacion,int fianza,int cantTurnos) {
        super("Carcel",TipoCasillero.CARCEL,ubicacion);
        this.fianza = fianza;
        this.cantTurnos = cantTurnos;
    }
}

