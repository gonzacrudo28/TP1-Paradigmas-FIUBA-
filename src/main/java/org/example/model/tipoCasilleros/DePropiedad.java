package org.example.model.tipoCasilleros;

import org.example.model.*;

import java.io.Serializable;

public class DePropiedad extends Casillero implements CasilleroEjecutable {
    private final Propiedad propiedad;

    public DePropiedad(int casillero, int precio, int numeroDeBarrio) {
        super("Propiedad", TipoCasillero.PROPIEDAD, casillero);
        this.propiedad = new Propiedad(precio, numeroDeBarrio, casillero);
    }

    //deberia ser get comprable
    public Propiedad getPropiedad() {
        return propiedad;
    }

    @Override
    public void ejecutarCasillero(Jugador jugador) {
        if (propiedad.getEstado() == Comprable.EstadoPropiedad.COMPRADO) {
            jugador.restarPlata(propiedad.getAlquiler());
            System.out.println(jugador.getNombre() + " pagaste alquiler por estar en la propiedad de " + propiedad.getNombrePropietario());
        }

    }
}


