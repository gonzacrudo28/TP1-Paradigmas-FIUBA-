package org.example.controller;

import org.example.model.Jugador;
import org.example.model.Propiedad;
import org.example.model.Tablero;
import org.example.model.tipoCasilleros.DePropiedad;
import org.example.model.tipoCasilleros.TipoCasillero;

public class ConsultarPrecios implements EjecutarAccion{
    public void ejecutar(Jugador jugador, int casilleroPropiedad, Tablero tablero, ConstruccionController controller){
        Propiedad propiedad = obtenerPropiedad(casilleroPropiedad, tablero);
        System.out.println("El precio de una casa en esa propiedad es " + propiedad.getPrecioCasa());
    }

    public Propiedad obtenerPropiedad(int casillero, Tablero tablero) {
        if (esPropiedad(casillero, tablero)) {
            DePropiedad casilleroPropiedad = tablero.getPropiedad(casillero);
            return casilleroPropiedad.getPropiedad();
        }
        System.out.println("Accion imposible de realizar");
        return null;
    }
    public boolean esPropiedad(int casillero, Tablero tablero) {
        if (casillero < tablero.getCantidadCasilleros()) {
            TipoCasillero tipoCasillero = tablero.getTipoCasillero(casillero);
            return tipoCasillero == TipoCasillero.PROPIEDAD;
        }
        return false;
    }

}
