package org.example.model;

import org.example.model.Tablero;

public class AdministradorDeMovimientos {
    private final int cantCasillas;

    public AdministradorDeMovimientos(Tablero tablero){
        this.cantCasillas = tablero.getCantidadCasilleros();
    }

    private boolean pasaPorSalida(Jugador jugador, int tiradaDados){
    return (jugador.getUbicacion() + tiradaDados) >= cantCasillas;
    }

    public int avanzarJugador(Jugador jugador,int dados){
        int posNueva = jugador.getUbicacion() + dados;
        if(pasaPorSalida(jugador, dados)) {
            posNueva -= cantCasillas;
            jugador.setUbicacion(posNueva);
            return posNueva;
        }
        jugador.setUbicacion(posNueva);
        return posNueva;

    }



}
