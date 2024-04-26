package org.example.model.tipoCasilleros;
public class Carcel extends Casillero{
    private double fianza;
    private int cantTurnos;

    public Carcel(int ubicacion,double fianza,int cantTurnos) {
        super("Carcel",TipoCasillero.CARCEL,ubicacion);
        this.fianza = fianza;
        this.cantTurnos = cantTurnos;
    }

    public double getFianza() {
        return this.fianza;
    }
}
