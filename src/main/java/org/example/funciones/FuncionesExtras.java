package org.example.funciones;

import org.example.model.*;
import org.example.model.tipoCasilleros.DePropiedad;
import org.example.model.tipoCasilleros.Estacion;
import org.example.model.tipoCasilleros.TipoCasillero;

import java.util.List;


public class FuncionesExtras {
    private Tablero tablero;
    private Juego juego;

    public FuncionesExtras(Juego juego) {
        this.tablero = juego.getTablero();
        this.juego = juego;

    }
        public boolean esPropiedad (int casillero){
            if (casillero < tablero.getCantidadCasilleros()) {
                TipoCasillero tipoCasillero = tablero.getTipoCasillero(casillero);
                return tipoCasillero == TipoCasillero.PROPIEDAD;
            }
            return false;
        }

        public Comprable obtenerComprableJugador ( int casillero, Jugador jugador){
            Comprable comprable = obtenerComprable(casillero);
            if (comprable != null && comprable.getPropietario() == jugador) {
                return comprable;
            }
            return null;
        }

        public Propiedad obtenerPropiedadJugador ( int casillero, Jugador jugador){
            if (esPropiedad(casillero)) {
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
        public boolean esComprable ( int casillero){
            TipoCasillero tipoCasillero = tablero.getTipoCasillero(casillero);
            return tipoCasillero == TipoCasillero.ESTACION ||
                    tipoCasillero == TipoCasillero.PROPIEDAD;
        }

        public void pagarBono (Jugador jugador,int dados, int casillaAnterior){
            if ((casillaAnterior + dados) >= tablero.getCantidadCasilleros()) {
                juego.pagarBono(jugador);
            }
        }

        public Comprable obtenerComprable ( int casillero){
            if (!esComprable(casillero)) {
                System.out.println("Accion imposible de realizar");
                return null;
            }
            Comprable comprable;
            if (esPropiedad(casillero)) {
                DePropiedad casilleroPropiedad = tablero.getPropiedad(casillero);
                comprable = casilleroPropiedad.getPropiedad();
                return comprable;
            }
            Estacion casilleroEstacion = tablero.getEstacion(casillero);
            comprable = casilleroEstacion.getEstacion();
            return comprable;
        }

        public Propiedad obtenerPropiedad ( int casillero){
            if (esPropiedad(casillero)) {
                DePropiedad casilleroPropiedad = tablero.getPropiedad(casillero);
                return casilleroPropiedad.getPropiedad();
            }
            System.out.println("Accion imposible de realizar");
            return null;
        }

}
