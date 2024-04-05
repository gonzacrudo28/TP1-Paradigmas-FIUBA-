package org.example.model;
import org.example.model.Banco;

public class AdministradorDeMovimientos {
    private Tablero tablero;

    public administradorDeMovimientos(Tablero tablero){
        this.tablero = tablero;
    }
    public boolean pasaPorSalida(Jugador jugador, int tiradaDados){
    return (jugador.getUbicacion() + tiradaDados) <= this.tablero.getCantidadCasilleros();
    }

    public void moverJugador(Jugador jugador, int tiradaDados, Banco banco){
        int nuevaPos= tiradaDados+jugador.getUbicacion();
        if(pasaPorSalida(jugador, tiradaDados)) {
            nuevaPos -= this.tablero.getCantidadCasilleros();
            jugador.setUbicacion(nuevoPos);
            banco.pagarBono(jugador);
            return;
        }else{
            jugador.setUbicacion(nuevaPos);
        }

    }
}
