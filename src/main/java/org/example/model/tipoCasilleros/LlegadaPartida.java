package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

public class LlegadaPartida extends Casillero implements CasilleroEjecutable{
    private double bono;

    public LlegadaPartida(int ubicacion,double bono) {
        super("Llegada/Partida",TipoCasillero.LLEGADA_INICIO,ubicacion);
        this.bono = bono;
    }

    public void ejecutarCasillero(Jugador jugador) {
        jugador.sumarPlata(bono);
        System.out.println(jugador.getNombre() +" diste una nueva vuelta! Aca tenes tus $"+bono);
    }

}