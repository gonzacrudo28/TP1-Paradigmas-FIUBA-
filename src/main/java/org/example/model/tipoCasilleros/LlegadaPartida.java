package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

public class LlegadaPartida extends Casillero{
    private int bono;
    public LlegadaPartida(Jugador jugador) {
        jugador.setPlata(jugador.getPlata() + this.bono);
    }
}
