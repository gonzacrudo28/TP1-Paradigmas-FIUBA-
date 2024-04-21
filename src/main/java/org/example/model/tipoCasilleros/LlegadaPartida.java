package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

public class LlegadaPartida extends Casillero implements CasilleroEjecutable{
    private int bono;
    public LlegadaPartida(int ubicacion,int bono) {
        super("LLegada/Partida",TipoCasillero.LLEGADA_INICIO,ubicacion);
        this.bono = bono;
    }

    public void ejecutarCasillero(Jugador jugador) {
        jugador.sumarPlata(bono);
        System.out.println(jugador.getNombre() +" diste una nueva vuelta! Aca tenes tus $"+bono);
    }
}
