package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

public class LlegadaPartida extends Casillero{
    private int bono;
    public LlegadaPartida() {
        this.efecto = "LLegadaPArtida";
        this.tipo=TipoCasillero.LLEGADA_INICIO;
    }
}
