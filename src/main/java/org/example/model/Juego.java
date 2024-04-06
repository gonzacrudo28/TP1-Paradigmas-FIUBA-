package org.example.model;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class Juego {
    private List<Jugador> jugadores;
    private Tablero tablero;
    private AdministradorTurnos administradorDeTurno;
    private AdministradorDeMovimientos administradorDeMovimientos;

    public Juego(List<Jugador> jugadores){
        this.jugadores = jugadores;
        this.tablero = new Tablero();
        this.administradorDeMovimientos = new AdministradorDeMovimientos(tablero);
        this.administradorDeTurno = new AdministradorTurnos(jugadores);
    }
    public void setJugador(Jugador jugador, int casilla){
        tablero.setJugador(casilla);
    }
    public Jugador getJugadorActual() {
        return administradorTurnos.obtenerJugador();
    }

    public Boolean terminado() {
        return checkEstadoJugadores(jugadores);
    }

    public void cambiarTurno() {
        administradorTurnos.siguienteTurno();
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public List<Jugador> getJugadores() {
        return this.jugadores;
    }

    public boolean checkEstadoJugadores(List<Jugador> jugadores){
        int jugadoresFuera = 0;
        for(Jugador jugador: jugadores){
            if (jugador.estaEnQuiebra()){
                jugadoresFuera++;
            }
        }
        return jugadoresFuera == jugadores.size()-1;
    }
}
