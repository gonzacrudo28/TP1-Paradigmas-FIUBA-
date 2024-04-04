package org.example.model;


import java.util.ArrayList;
import java.util.Random;


public class AdministradorTurnos {
    private int turnoActual;
    private final ArrayList<Jugador> jugadores;
    private Jugador actual;
    public AdministradorTurnos(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.turnoActual = 0;
    }
    public int getTurnoActual() {return this.turnoActual;}

    public Jugador avanzarTurno(){
        //NICO: FALTA CHECKEAR SI EL JUGADOR NO PERDIO ANTES
        if (this.turnoActual + 1 < this.jugadores.size() - 1){
            Jugador actual = jugadores.get(this.turnoActual + 1);
        } else{
            this.turnoActual = 0;
            Jugador actual = jugadores.get(0);
        }
        this.turnoActual++;
        return actual;
    }

    public int tirarDados(){
        Random random = new Random();
        return random.nextInt(2,12);
    }

    public void avanzar(Jugador jugador) {
        jugador.avanzarJugador(tirarDados());
    }



}
