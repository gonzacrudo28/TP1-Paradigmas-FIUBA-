package org.example.model.tipoCasilleros;

import org.example.model.Colores;
import org.example.model.Jugador;
import org.example.model.Propiedad;

public class DePropiedad extends Casillero{
    private final Propiedad propiedad;

    public DePropiedad(int casillero, int precio, int numeroDeBarrio) {
        super("Propiedad",TipoCasillero.PROPIEDAD,casillero);
        this.propiedad = new Propiedad(precio, numeroDeBarrio, casillero);
    }
    //deberia ser get comprable
    public Propiedad getPropiedad() {
        return propiedad;
    }

    @Override
    public int getPrecio(){
        return propiedad.getPrecio();
    }
}
