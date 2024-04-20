package org.example.model.tipoCasilleros;

public class Estacion extends  Casillero{
    private double precio;
    public Estacion(double precio){
        this.precio = precio;
        this.efecto = "De transporte";
        this.tipo = TipoCasillero.ESTACION;
    }
    public double getPrecio() {
        return precio;
    }
}
