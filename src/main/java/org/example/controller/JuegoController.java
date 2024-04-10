package org.example.controller;

import org.example.model.AdministradorDeMovimientos;
import org.example.model.Juego;
import org.example.model.Jugador;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.TerminalBuilder;
import org.jline.terminal.Terminal;
import org.example.view.JuegoView;
import org.example.model.Acciones;
import org.example.view.TableroView;

import org.example.controller.Constantes;

import java.io.IOException;
import java.util.Scanner;

public class JuegoController {
    private final Juego juego;
    private JuegoView vistaJuego;
    private LineReader reader;
    private AdministradorDeMovimientos administradorDeMovimientos;
    private final TableroView tableroView;

    public JuegoController(Juego juego) throws IOException {
        this.tableroView = new TableroView(juego.getTablero());
        this.juego = juego;
        this.vistaJuego = new JuegoView(juego);
        this.administradorDeMovimientos = new AdministradorDeMovimientos(juego.getTablero().cantCasilleros);
        Terminal terminal = TerminalBuilder.terminal();
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
    }

    public void jugarTurno() {
        vistaJuego.mostrar();
        tableroView.mostrar();
        juego.cambiarTurno();

        Jugador jugador = juego.getJugadorActual();
        int dados = juego.tirarDados();
        System.out.println("Es el turno de "+jugador.getNombre()+"\n"+"Tus dados son: "+dados+"\n");
        int casillaActual = administradorDeMovimientos.avanzarJugador(jugador,dados);
        System.out.println("Usted esta en el casillero: "+casillaActual+"\n");
        int numeroElecto = 1;
        while(numeroElecto != 0){
            Acciones.mostrarAcciones();
            String accion = reader.readLine("Seleccione la accion que quiere realizar indicando su numero (NUMERO):");
            numeroElecto = corroboroAccion(accion);
            if(numeroElecto == Constantes.NEGATIVO){
                System.out.println("Accion inexistente");
            }
            Acciones.Accion accionElecta = Acciones.Accion.getAccion(numeroElecto);
            if(accionElecta == null){
                System.out.println("Accion inexistente");
            }
        }

    }

    public int corroboroAccion(String accion){
        CheckStrToInt checkStrToInt = new CheckStrToInt();
        if (checkStrToInt.checkStringToInt(accion)){
            return Constantes.NEGATIVO;
        } else if (Integer.parseInt(accion) < 0) {
            return Constantes.NEGATIVO;
        }
        return Integer.parseInt(accion);
        }
/*
Luego de esto, el jugador podrá realizar cualquier de las siguientes acciones ilimitadamente,
hasta terminar su turno:
● Terminar su turno
● Construir o reformar en una de sus propiedades
● Vender alguna de sus construcciones
● Hipotecar una propiedad al banco
● Des-hipotecar una de sus propiedades
● En caso de estar en preso:
    ○ Pagar la fianza
● En caso de estar sobre una casilla de propiedad vacía:
    ○ Comprar la propiedad

*/


    //Nico: no va aca sino en el del view, ver tatedrez


    /*
    public void setJuego(Juego juego){
        this.juego = juego;
        List<Jugador> jugadores = juego.getJugadores();
        Jugador jugador1 = jugadores.get(0);
        Jugador jugador2 = jugadores.get(1);
        this.jugador1.setText(jugadores.get(0).getNombre());
        this.jugador2.setText(jugadores.get(1).getNombre());
        this.jugador1_color.setFill(colores.get(jugador1.getColor()));
        this.jugador2_color.setFill(colores.get(jugador2.getColor()));
        this.jugadorActual.setText("Jugador actual: " + juego.getJugadorActual().getNombre());
        tableroController.setJuego(juego);
   }*/


}