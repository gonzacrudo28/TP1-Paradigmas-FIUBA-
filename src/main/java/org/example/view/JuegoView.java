package org.example.view;

import org.example.model.Jugador;
import org.example.model.Tablero;
import java.util.ArrayList;
import java.util.HashMap;
import org.example.model.Juego;

public class JuegoView {
    private ArrayList<Jugador> jugadores;
    private TableroView tableroView;
    private HashMap<Jugador, JugadorView> jugadorView;
    private Juego juego;

    public JuegoView(Juego juego){
        this.jugadores = juego.getJugadores();
        this.juego = juego;
        this.tableroView = new TableroView(juego.getTablero());
    }
    public void mostrar(){
        tableroView.mostrar(jugadores);
        JugadorView jugadorView = new JugadorView(jugadores);
        jugadorView.mostrarJugadores();
    }
    public void muestaJugadoresInicial(){
        JugadorView jugadorView = new JugadorView(jugadores);
        jugadorView.mostrarJugadores();
    }

}
