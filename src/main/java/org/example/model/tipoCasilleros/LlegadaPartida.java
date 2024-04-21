package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

public class LlegadaPartida extends Casillero{
    private int bono;
    public LlegadaPartida(int ubicacion,int bono) {
        super("LLegadaPArtida",TipoCasillero.LLEGADA_INICIO,ubicacion);
        this.bono = bono;
    }
}
