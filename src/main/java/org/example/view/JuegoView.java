package org.example.view;

import org.example.model.Jugador;
import org.example.model.Tablero;
import java.util.List;
import java.util.HashMap;
import org.example.model.Juego;

public class JuegoView {
    private List<Jugador> jugadores;
    private TableroView tableroView;
    private HashMap<Jugador, JugadorView> jugadorView;
    private Juego juego;

    public JuegoView(Juego juego){
        this.jugadores = juego.getJugadores();
        this.juego = juego;
        this.tableroView = new TableroView(juego.getTablero());
    }
    public void mostrar(){
        tableroView.mostrar();
        Jugador actual = this.juego.getJugadorActual();
        JugadorView jugadorView = new JugadorView(jugadores);
        jugadorView.mostrarJugadores();
    }
}
