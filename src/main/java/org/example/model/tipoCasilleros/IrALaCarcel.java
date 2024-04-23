package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

public class IrALaCarcel extends Casillero implements CasilleroEjecutable{
    private final int duracionPena;
    private final int ubicacionCarcel;
    public IrALaCarcel(int ubicacion, int duracionPena,int ubicacionCarcel) {
        super("IrALaCarcel",TipoCasillero.IR_A_LA_CARCEL,ubicacion);
        this.duracionPena = duracionPena;
        this.ubicacionCarcel = ubicacionCarcel;
    }

    public void ejecutarCasillero(Jugador jugador) {
        jugador.setEstado(Jugador.Estado.Preso);
        jugador.setCondena(duracionPena);
        jugador.setUbicacion(ubicacionCarcel);
        System.out.printf("Ups! %s se va preso %d turnos\n", jugador.getNombre(), duracionPena);
    }
}
