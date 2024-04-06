package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class CrearJugadores {
    public List<Jugador> crearJugadores(String nombres){
        List<String> nombre = List.of(nombres.split(" "));
        List<Jugador> jugadores = new ArrayList<Jugador>();
        for (String s : nombre) {
            jugadores.add(new Jugador(s));
        }
        return jugadores;
    }
}
