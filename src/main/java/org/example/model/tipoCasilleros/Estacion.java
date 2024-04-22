package org.example.model.tipoCasilleros;

import org.example.model.EstacionTransporte;
import org.example.model.Propiedad;

public class Estacion extends Casillero{
    private double precio;
    private final EstacionTransporte estacion;

    public Estacion(int ubicacion,double precio){
        super("De transporte",TipoCasillero.ESTACION,ubicacion);
        this.precio = precio;
        this.estacion = new EstacionTransporte(precio,ubicacion);
    }

    @Override
    public int getPrecio() {
        return (int) precio;
    }
   //deberia ser get comprable
    public EstacionTransporte getPropiedad() { return estacion; }
}
