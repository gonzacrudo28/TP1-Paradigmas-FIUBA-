package org.example.model;

import org.example.model.Jugador;

import Java.util.ArrayList;

import Java.util.Random;

import java.util.ArrayList;
import java.util.Random;

public class AdministradorTurnos {
    private int turnoActual;
    private ArrayList<Jugador> jugadores;
    private Jugador actual;
    public AdministradorTurnos(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.turnoActual = 0;
    }
    public int getTurnoActual() {return this.turnoActual;}

    public Jugador AvanzarTurno(){
        if (this.turnoActual + 1 < this.jugadores.size() - 1){
            Jugador actual = jugadores.get(this.turnoActual + 1);
            this.turnoActual++;
            return actual;
        } else{
            this.turnoActual = 0;
            Jugador actual = jugadores.get(0);
            return actual;
        }
    }

    public int TirarDados(Jugador jugador){
        Random random = new Random();
        int randomNumber = random.nextInt(2,12);
        return randomNumber;
    }

    public void Avanzar(Jugador jugador, int posiciones) {
        jugador.setUbicacion(posiciones);
    }



}
