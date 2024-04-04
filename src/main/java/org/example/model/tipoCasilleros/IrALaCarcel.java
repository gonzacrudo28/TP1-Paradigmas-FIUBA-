package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

public class IrALaCarcel extends Casillero{
    private int duracionPena;
    public IrALaCarcel(Jugador jugador) {
        jugador.setCondena(this.duracionPena);
    }
}
