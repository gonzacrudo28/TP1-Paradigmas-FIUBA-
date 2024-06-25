package org.example.model;

import java.util.ArrayList;
import java.util.List;
import org.example.controller.AdministradorTurnos;
import org.example.controller.Configuracion;
import org.example.funciones.FuncionColorPrints;
import org.fusesource.jansi.Ansi;

public class Juego {
    private ArrayList<Jugador> jugadores;
    private Tablero tablero;
    private AdministradorTurnos administradorDeTurnos;
    private Configuracion configuracion;
    private Banco banco;

    public Juego(List<String> configuraciones){
        Configuracion configuracion = new Configuracion(configuraciones);
        this.configuracion = configuracion;
        jugadores = configuracion.getJugadores();
        this.administradorDeTurnos = new AdministradorTurnos(jugadores);
        this.tablero = new Tablero(configuracion);
        this.banco = new Banco(configuracion.getMontoVuelta());
    }

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

    public int tirarDados(){
        return administradorDeTurnos.tirarDados(configuracion.getCantidadDeLadosEnDado());
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public ArrayList<Jugador> getJugadores() {
        return this.jugadores;
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
/* --------------------------------primeros cambios en juego----------------------------------------------------*/
    public String empezarTurno(Jugador jugador){
        Ansi colorANSI = null;
        Ansi resetColor = Ansi.ansi().reset();
        FuncionColorPrints funcionColorPrints = new FuncionColorPrints();
        colorANSI = funcionColorPrints.obtenerColorANSI(jugador.getColor());
        String acciones = jugador.obtenerAccionesDisponibles(colorANSI);
        return acciones;
    }

    public String realizarJuego(Jugador jugador){
        if (jugador.getEstado().equals(Estado.Preso)){
            return jugador.obtenerAccionesDisponibles() + juegoDePreso(jugador);
        }
        else{
            return jugador.obtenerAccionesDisponibles() + juegoDeLibre(jugador);
        }
    }


}
