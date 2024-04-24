package org.example.model.tipoCasilleros;

import org.example.model.*;

import java.io.Serializable;

public class DePropiedad extends Casillero implements CasilleroEjecutable {
    private final Propiedad propiedad;

    public DePropiedad(int casillero, int precio, int numeroDeBarrio) {
        super("Propiedad", TipoCasillero.PROPIEDAD, casillero);
        this.propiedad = new Propiedad(precio, numeroDeBarrio, casillero);
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public double getPrecio() {
        return propiedad.getPrecio();
    }
    @Override
    public void ejecutarCasillero(Jugador jugador) {
        if (propiedad.getEstado() == Comprable.EstadoPropiedad.COMPRADO) {
            double alquiler = propiedad.getAlquiler();
            jugador.restarPlata(alquiler);
            System.out.printf("%s pagaste %f de alquiler por estar en la propiedad de %s\n",jugador.getNombre(),alquiler,propiedad.getNombrePropietario());
        }

    }
}


