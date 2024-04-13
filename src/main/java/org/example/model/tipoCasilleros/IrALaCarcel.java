package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

public class IrALaCarcel extends Casillero{
    private int duracionPena;
    public IrALaCarcel() {
        this.efecto= "IrALaCarcel";
        this.tipo=TipoCasillero.IR_A_LA_CARCEL;

    }
}
