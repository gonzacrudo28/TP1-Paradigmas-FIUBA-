package org.example.model;

import java.text.CompactNumberFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import org.example.controller.CheckArgumentos;
import org.example.controller.CheckArgumentos.ConfiguracionCheckArgumentos;
import org.example.controller.Configuracion;

import org.example.model.Colores;
import java.util.concurrent.ScheduledExecutorService;

public class Juego {
    private ArrayList<Jugador> jugadores;
    private Tablero tablero;
    private AdministradorTurnos administradorDeTurnos;
    private AdministradorDeMovimientos administradorDeMovimientos;
    private Configuracion configuracion;

    public Juego(List<String> configuraciones){
        Configuracion configuracion = new Configuracion(configuraciones);
        jugadores = configuracion.getJugadores();
        this.administradorDeTurnos = new AdministradorTurnos(jugadores);
        int cantCasillas = configuracion.getCantidadCasilleros();
        int costoMulta = configuracion.getMontoMulta();
        int costoPasoSalida = configuracion.getMontoPorPasarSalida();
        int turnosPreso = configuracion.getCondenaCarcel();
        this.tablero = new Tablero(cantCasillas,costoMulta,costoPasoSalida,turnosPreso);
        this.administradorDeMovimientos = new AdministradorDeMovimientos(tablero);
    }

//    public void setJugador(Jugador jugador, int casilla){
//        tablero.avanzarJugador(casilla);
//    }

    public Jugador getJugadorActual() {
       return administradorDeTurnos.getTurnoActual();}

    public Boolean terminado() {
        return checkEstadoJugadores(jugadores);
    }

    public void cambiarTurno() {
        administradorDeTurnos.avanzarTurno();
    }

    public int tirarDados(){
        return administradorDeTurnos.tirarDados();
    }
    public Tablero getTablero() {
        return this.tablero;
    }

    public ArrayList<Jugador> getJugadores() {
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
