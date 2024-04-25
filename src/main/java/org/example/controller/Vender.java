package org.example.controller;

import org.example.model.EstacionTransporte;
import org.example.model.Jugador;
import org.example.model.Propiedad;
import org.example.model.Tablero;
import org.example.model.Comprable;
import org.example.controller.ConstruccionController;
import org.example.model.tipoCasilleros.DePropiedad;
import org.example.model.tipoCasilleros.Estacion;
import org.example.model.tipoCasilleros.TipoCasillero;

public class Vender implements EjecutarAccion{
    @Override
    public void ejecutar(Jugador jugador, int casilleroComprable, Tablero tablero, ConstruccionController controller) {
        Comprable comprable = obtenerComprableJugador(casilleroComprable, jugador,tablero);
        if (comprable != null) {
            if(comprable instanceof Propiedad){
                controller.vender(jugador,(Propiedad) comprable);
            }else if (comprable instanceof EstacionTransporte){
                jugador.venderEstacion(comprable);
            }
        }
    }
    public Comprable obtenerComprableJugador(int casillero, Jugador jugador, Tablero tablero){
        Comprable comprable = obtenerComprable(casillero,tablero);
        if(comprable != null && comprable.getPropietario()==jugador){
            return comprable;
        }
        return null;
    }
    public Comprable obtenerComprable(int casillero, Tablero tablero) {
        if(!esComprable(casillero,tablero)){
            System.out.println("Accion imposible de realizar");
            return null;
        }
        Comprable comprable;
        if(esPropiedad(casillero,tablero)){
            DePropiedad casilleroPropiedad = tablero.getPropiedad(casillero);
            comprable = casilleroPropiedad.getPropiedad();
            return comprable;
        }
        Estacion casilleroEstacion = tablero.getEstacion(casillero);
        comprable = casilleroEstacion.getEstacion();
        return comprable;
    }
    public boolean esComprable(int casillero,Tablero tablero) {
        TipoCasillero tipoCasillero = tablero.getTipoCasillero(casillero);
        return tipoCasillero == TipoCasillero.ESTACION ||
                tipoCasillero == TipoCasillero.PROPIEDAD;
    }

    public boolean esPropiedad(int casillero,Tablero tablero) {
        if (casillero < tablero.getCantidadCasilleros()) {
            TipoCasillero tipoCasillero = tablero.getTipoCasillero(casillero);
            return tipoCasillero == TipoCasillero.PROPIEDAD;
        }
        return false;
    }
}
