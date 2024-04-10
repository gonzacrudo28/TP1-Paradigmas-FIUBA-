package org.example;


import org.example.controller.JuegoController;
import org.example.model.*;
import org.example.controller.CheckArgumentos;
import org.example.model.tipoCasilleros.Casillero;
import org.example.view.JugadorView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        CheckArgumentos check = new CheckArgumentos();
        check.CheckArgumentos();
        List<String> argumentos = check.getConfiguraciones();
        Juego juego = new Juego(argumentos);
        List<Jugador> listaJugadores = juego.getJugadores();
        JuegoController juegoController = new JuegoController(juego);
        for (Jugador jugador : listaJugadores) {
            JugadorView jugadorView = new JugadorView(jugador);
            jugadorView.mostrarJugador();
        }
        while (!juego.terminado()){
            juegoController.jugarTurno();
        }
        check.CerrarScanner();
        System.out.println("El juego terminado");
    }
    

}

/*
QUE TENEMOS:
    CREAMOS EL JUEGO
    CREAMOS LAS CONFIGURACIONES
    CREAMOS LOS JUGADORES
    GENERAMOS UN ORDEN DE JUEGO

COSAS PENDIENTES:
*       TIRAR DADO
*       CREAR TABLERO
*       MOSTRAR TABLERO
*
*       OPCIONES DEL USUARIO
        IMPLEMENTAR BANCO
        IMPLEMENTAR BANCARROTA
        IMPLEMENTAR FIN DE PARTIDA
*
* */


