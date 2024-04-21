package org.example.model.tipoCasilleros;

public class Estacion extends  Casillero{
    private double precio;
    public Estacion(int ubicacion,double precio){
        super("De transporte",TipoCasillero.ESTACION,ubicacion);
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
}
