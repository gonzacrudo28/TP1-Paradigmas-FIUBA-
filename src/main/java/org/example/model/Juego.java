package org.example.model;

import java.util.ArrayList;
import java.util.List;

import org.example.controller.AdministradorDeMovimientos;
import org.example.controller.AdministradorTurnos;
import org.example.controller.Configuracion;

public class Juego {
    private ArrayList<Jugador> jugadores;
    private Tablero tablero;
    private AdministradorTurnos administradorDeTurnos;
    private AdministradorDeMovimientos administradorDeMovimientos;
    private Configuracion configuracion;
    private Banco banco;

    /*
Juego(List<String>)
double getFianza()
Jugador getJugadorActual()
Boolean terminado()


    */

    public Juego(List<String> configuraciones){
        Configuracion configuracion = new Configuracion(configuraciones);
        this.configuracion = configuracion;
        jugadores = configuracion.getJugadores();
        this.administradorDeTurnos = new AdministradorTurnos(jugadores);
        this.tablero = new Tablero(configuracion);
        this.administradorDeMovimientos = new AdministradorDeMovimientos(tablero);
        this.banco = new Banco(configuracion.getMontoVuelta());
    }
    public double getFianza(){return configuracion.getMontoFianza();}
    public Jugador getJugadorActual() {
       return administradorDeTurnos.getTurnoActual();
    }

    public Boolean terminado() {
        return checkEstadoJugadores(jugadores);
    }

    public void eliminarJugador(Jugador jugador){
        jugadores.remove(jugador);
    }
    public void cambiarTurno() {
        administradorDeTurnos.avanzarTurno();
    }
/*
eliminarJugador(Jugador jugador)
cambiarTurno()
Tablero getTablero()





*/
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
        for(Jugador jugador: jugadores){
            if (jugador.getEstado().equals(Estado.Gano)){
                return true;
            }
        }
        return jugadores.size() == 1;
    }
}
