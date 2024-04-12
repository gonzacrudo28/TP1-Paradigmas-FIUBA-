package org.example.controller;

import org.example.model.Colores;
import org.example.model.Jugador;
import org.example.model.Tablero;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;


public class Configuracion {
    private int montoVuelta;
    private int montoInicial;
    private int condenaCarcel;
    private final int montoDeMulta;
    private int cantidadCasilleros;
    private int montoFianza;
    private List<Jugador> jugadores;


    public Configuracion(List<String> configuraciones/*, Tablero tablero*/) {
        cantidadCasilleros = Integer.parseInt(configuraciones.get(CheckArgumentos
                .ConfiguracionCheckArgumentos.CASILLEROS.ordinal()));
        montoInicial = Integer.parseInt(configuraciones.get(CheckArgumentos
                .ConfiguracionCheckArgumentos.DINERO_INICIAL.ordinal()));
        montoVuelta = Integer.parseInt(configuraciones.get(CheckArgumentos
                .ConfiguracionCheckArgumentos.DINERO_VUELTA.ordinal()));
        condenaCarcel = Integer.parseInt(configuraciones.get(CheckArgumentos
                .ConfiguracionCheckArgumentos.TURNOS_PRESO.ordinal()));
        montoDeMulta = Integer.parseInt(configuraciones.get(CheckArgumentos
                .ConfiguracionCheckArgumentos.MULTA.ordinal()));
        montoFianza = Integer.parseInt(configuraciones.get(CheckArgumentos
                .ConfiguracionCheckArgumentos.FIANZA.ordinal()));

        List<Jugador> jugadores = this.crearJugadores(configuraciones);
        this.jugadores = jugadores;
        asignacionColores(jugadores);
    }

    private List<Jugador> crearJugadores(List<String> configuraciones){
        List<String> nombres = List.of(configuraciones.get(0).split(" "));
        int plataInicial = Integer.parseInt(configuraciones.get(2));
        List<Jugador> jugadores = new ArrayList<Jugador>();
        for (String s : nombres) {
            Jugador jugador = new Jugador(s);
            jugador.setPlata(plataInicial);
            jugadores.add(jugador);
        }

        return jugadores;
    }

    private void asignacionColores(List<Jugador> jugadores){
        Colores.Color[] colores = Colores.Color.values();
        for(int i = 0; i < jugadores.size(); i++){
            jugadores.get(i).setColor(colores[i]);
        }
    }

/*
    public ArrayDeque<Jugador> creacionColaTurnos(){
        ArrayDeque<Jugador> colaTurnos = new ArrayDeque<>();
        for (Jugador jugadores : jugadores) {
            colaTurnos.addLast(jugadores);
        }
        return colaTurnos;
    }
*/
    public void getJugadores() { return jugadores;}

    public int getMontoFianza() { return montoFianza; }

    public void setTablero(Tablero tablero) { this.tablero = tablero; }

    public int getCantidadCasilleros() { return cantidadCasilleros; }

    public int getMontoPorPasarSalida(){return montoPorPasarSalida;}

    public Tablero getTablero(){
        return this.tablero;
    }

}
