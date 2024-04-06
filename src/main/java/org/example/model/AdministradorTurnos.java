package org.example.model;


import java.util.List;
import java.util.Random;


public class AdministradorTurnos {
    private int turnoActual;
    private final List<Jugador> jugadores;
    private Jugador actual;

    public AdministradorTurnos(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.turnoActual = 0;
    }
    public int getTurnoActual() {return this.turnoActual;}

    private boolean siguientePerdio(int jugadorSiguiente){
        Jugador jugador = jugadores.get(jugadorSiguiente);
        return jugador.getEstado() == Jugador.Estado.Quiebra;
    }

    public Jugador avanzarTurno(){
        //NICO: FALTA CHECKEAR SI EL JUGADOR NO PERDIO ANTES, esta funcion esta mal hecha
        this.turnoActual++ ;
        // Agregue un -1 xq me parece que el size es como el len, tiene 1 de mas -G
        if (this.turnoActual == this.jugadores.size()-1){this.turnoActual = 0;}

        if (this.siguientePerdio(this.turnoActual))
            avanzarTurno();
        else {
            actual = this.jugadores.get(this.turnoActual + 1);
            return actual;
        }
        return actual;
    }

    public int tirarDados(){
        Random random = new Random();
        return random.nextInt(2,12);
    }

    /*public void avanzar(Jugador jugador)
        jugador.avanzarJugador(tirarDados());
    faq: este metodo se implementa en admin de movs, jugador  */

}
