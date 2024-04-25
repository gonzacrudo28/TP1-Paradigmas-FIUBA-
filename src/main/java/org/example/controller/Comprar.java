package org.example.controller;

import org.example.model.Comprable;
import org.example.model.Jugador;
import org.example.model.Tablero;
import org.example.model.tipoCasilleros.TipoCasillero;
import org.example.model.tipoCasilleros.DePropiedad;
import org.example.model.tipoCasilleros.Estacion;

public class Comprar implements EjecutarAccion{
    public void ejecutar(Jugador jugador, int propiedad, Tablero tablero, ConstruccionController controller) {
        int ubicacionJugador = jugador.getUbicacion();
        if (esComprable(ubicacionJugador, tablero)) {
            Comprable comprable = obtenerComprable(ubicacionJugador, tablero);
            jugador.comprarComprable(comprable);
        }
    }
    public boolean esComprable(int casillero, Tablero tablero) {
        TipoCasillero tipoCasillero = tablero.getTipoCasillero(casillero);
        return tipoCasillero == TipoCasillero.ESTACION ||
                tipoCasillero == TipoCasillero.PROPIEDAD;
    }

    public Comprable obtenerComprable(int casillero, Tablero tablero) {
        if(!esComprable(casillero, tablero)){
            System.out.println("Accion imposible de realizar");
            return null;
        }
        Comprable comprable;
        if(esPropiedad(casillero, tablero)){
            DePropiedad casilleroPropiedad = tablero.getPropiedad(casillero);
            comprable = casilleroPropiedad.getPropiedad();
            return comprable;
        }
        Estacion casilleroEstacion = tablero.getEstacion(casillero);
        comprable = casilleroEstacion.getEstacion();
        return comprable;
    }
    public boolean esPropiedad(int casillero, Tablero tablero) {
        if (casillero < tablero.getCantidadCasilleros()) {
            TipoCasillero tipoCasillero = tablero.getTipoCasillero(casillero);
            return tipoCasillero == TipoCasillero.PROPIEDAD;
        }
        return false;
    }

}
