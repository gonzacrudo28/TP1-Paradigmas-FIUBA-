package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

public class IrALaCarcel extends Casillero{
    private int duracionPena;
    public IrALaCarcel(int ubicacion, int duracionPena) {
        super("IrALaCarcel",TipoCasillero.IR_A_LA_CARCEL,ubicacion);
        this.duracionPena = duracionPena;
    }
}
