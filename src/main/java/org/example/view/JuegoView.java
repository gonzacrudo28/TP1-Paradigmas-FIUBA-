package org.example.view;

import org.example.funciones.FuncionesExtras;
import org.example.model.Jugador;
import java.util.ArrayList;
import java.util.HashMap;
import org.example.model.Juego;

public class JuegoView {
    private ArrayList<Jugador> jugadores;
    private TableroView tableroView;

    public JuegoView(Juego juego){
        this.jugadores = juego.getJugadores();
        this.tableroView = new TableroView(juego.getTablero());
    }
    public void mostrar(){
        FuncionesExtras.delay(1000);
        tableroView.mostrar(jugadores);
        FuncionesExtras.delay(1000);
        JugadorView jugadorView = new JugadorView(jugadores);
        jugadorView.mostrarJugadores();
        FuncionesExtras.delay(1000);
    }
    public void muestaJugadoresInicial(){
        JugadorView jugadorView = new JugadorView(jugadores);
        jugadorView.mostrarJugadores();
    }
}
