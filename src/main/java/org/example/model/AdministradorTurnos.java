package org.example.model;


import java.util.*;


public class AdministradorTurnos {
    private List<Jugador> jugadores;
    private Jugador actual;
    private Queue<Jugador> turnos ;

    public AdministradorTurnos(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.turnos = this.colaDeTurnos();
    }
    public Jugador getTurnoActual() {
        return this.turnos.peek();
    }

    public void generarOrdenDePaso(){
        Collections.shuffle(jugadores);
    }

    public Queue<Jugador> colaDeTurnos() {
        Queue<Jugador> turnos = new ArrayDeque<>();
        assert jugadores != null;
        for (Jugador jugador : jugadores) {
            turnos.offer(jugador);
        }
        return turnos;
    }


    private boolean siguientePerdio(int jugadorSiguiente){
        Jugador jugador = jugadores.get(jugadorSiguiente);
        return jugador.getEstado() == Jugador.Estado.Quiebra;
    }

    public void avanzarTurno(){
        Jugador jugador = turnos.poll();
        turnos.offer(jugador);
    }


    public int tirarDados(){
        Random random = new Random();
        return random.nextInt(2,12);
    }


}
