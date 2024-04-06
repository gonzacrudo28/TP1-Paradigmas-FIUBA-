package org.example.model;

public class AdministradorDeMovimientos {
    private Tablero tablero;

    public AdministradorDeMovimientos(Tablero tablero){
        this.tablero = tablero;
    }

    private boolean pasaPorSalida(Jugador jugador, int tiradaDados){
    return (jugador.getUbicacion() + tiradaDados) <= this.tablero.getCantidadCasilleros();
    }

    public void moverJugador(Jugador jugador, int tiradaDados, Banco banco){
        int nuevaPos= tiradaDados+jugador.getUbicacion();
        if(pasaPorSalida(jugador, tiradaDados)) {
            nuevaPos -= this.tablero.getCantidadCasilleros();
            jugador.setUbicacion(nuevaPos);
            banco.pagarBono(jugador);
        }else
            jugador.setUbicacion(nuevaPos);
    }
}
