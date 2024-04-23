package org.example.model.tipoCasilleros;

import org.example.model.Comprable;
import org.example.model.EstacionTransporte;
import org.example.model.Jugador;
import org.example.model.Propiedad;

import java.io.Serializable;

public class Estacion extends Casillero implements CasilleroEjecutable{
    private double precio;
    private final EstacionTransporte estacion;

    public Estacion(int ubicacion,double precio){
        super("De transporte",TipoCasillero.ESTACION,ubicacion);
        this.precio = precio;
        this.estacion = new EstacionTransporte(precio,ubicacion);
    }

    @Override
    public void ejecutarCasillero(Jugador jugador) {
        int canEstaciones = jugador.getEstaciones().size();
        if (estacion.getPropietario() != null) {
            if (canEstaciones == 0) {
                jugador.restarPlata((int) precio);
                System.out.println("Pagas alquiler por caer en la estacion de " + estacion.getNombrePropietario());
                //Los jugadores que posean alguna estación, están exentos de abonar el
                //monto al caer en la estación de otro jugador.

            } else {
                int cantEstaciones = jugador.getEstaciones().size();
                jugador.restarPlata(cantEstaciones * estacion.getAlquiler());
                //El monto a pagar se multiplicará por cada casilla de estación de transporte
                //que posea el jugador propietario de dicha estación.
            }
        }
    }

    public EstacionTransporte getEstacion(){ return this.estacion; }
    public double getPrecio(){ return this.precio; }
}
