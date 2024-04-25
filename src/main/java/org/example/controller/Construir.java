package org.example.controller;

import org.example.funciones.FuncionesExtras;
import org.example.model.Jugador;
import org.example.model.Tablero;
import org.example.model.Propiedad;
import org.example.model.tipoCasilleros.TipoCasillero;
import org.example.controller.ConstruccionController;


import java.util.List;

public class Construir implements EjecutarAccion{
    
    public void ejecutar(Jugador jugador, int propiedad, Tablero tablero, ConstruccionController controller) {
        Propiedad prop = obtenerPropiedadJugador(propiedad, jugador, tablero);
        if (prop != null) {
            controller.construirEnPropiedad(jugador, prop);
        }
    }
    public Propiedad obtenerPropiedadJugador(int casillero, Jugador jugador, Tablero tablero) {
        if (esPropiedad(casillero, tablero)) {
            List<Propiedad> propiedadList = jugador.getPropiedades();
            for (Propiedad propiedad : propiedadList) {
                if (propiedad.getUbicacion() == casillero) {
                    return propiedad;
                }
            }
            System.out.println("Esa propiedad no te pertenece");
            return null;
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
