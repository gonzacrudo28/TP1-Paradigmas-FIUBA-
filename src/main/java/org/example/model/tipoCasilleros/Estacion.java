package org.example.model.tipoCasilleros;

import org.example.model.Comprable;
import org.example.model.EstacionTransporte;
import org.example.model.Jugador;
import org.example.model.Propiedad;

import java.io.Serializable;

public class Estacion extends Casillero implements CasilleroEjecutable{
    private double precio;
    private final EstacionTransporte estacion;
    private Jugador propietario;

    public Estacion(int ubicacion,double precio){
        super("De transporte",TipoCasillero.ESTACION,ubicacion);
        this.precio = precio;
        this.estacion = new EstacionTransporte(precio,ubicacion);
        this.propietario = estacion.getPropietario();
    }

    @Override
    public void ejecutarCasillero(Jugador jugador) {
        int cantEstaciones = jugador.getEstaciones().size();
        if (estacion.getPropietario() != null || propietario == jugador) {
            if (cantEstaciones == 0) {
                double precio = estacion.getAlquiler() * propietario.getEstaciones().size();
                jugador.restarPlata(precio);
                System.out.printf("%s pagas %f de alquiler por caer en la estacion de %s",propietario.getNombre(),precio, estacion.getNombrePropietario());
                propietario.sumarPlata(precio);
            }
        }
    }

    public EstacionTransporte getEstacion(){ return this.estacion; }
    public double getPrecio(){ return this.precio; }
}
