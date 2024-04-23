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
    private Banco banco;

    public Juego(List<String> configuraciones){
        Configuracion configuracion = new Configuracion(configuraciones);
        this.configuracion = configuracion;
        jugadores = configuracion.getJugadores();
        this.administradorDeTurnos = new AdministradorTurnos(jugadores);
        this.tablero = new Tablero(configuracion);
        this.administradorDeMovimientos = new AdministradorDeMovimientos(tablero);
        this.administradorDeMovimientos = administradorDeMovimientos;
        this.banco = new Banco(configuracion.getMontoVuelta());
    }
    public double getFianza(){return configuracion.getMontoFianza();}
    public Jugador getJugadorActual() {
       return administradorDeTurnos.getTurnoActual();
    }

    public Boolean terminado() {
        return checkEstadoJugadores(jugadores);
    }

    public void cambiarTurno() {
        administradorDeTurnos.avanzarTurno();
    }

    public int tirarDados(){
        return administradorDeTurnos.tirarDados(configuracion.getCantidadDeLadosEnDado());
    }
    public Tablero getTablero() {
        return this.tablero;
    }

    public ArrayList<Jugador> getJugadores() {
        return this.jugadores;
    }

    public Configuracion getConfiguracion() {
        return this.configuracion;
    }

    public void pagarBono(Jugador jugador){
        banco.pagarBono(jugador);
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
