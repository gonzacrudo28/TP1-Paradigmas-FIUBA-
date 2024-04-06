package org.example;


import org.example.controller.JuegoController;
import org.example.model.*;
import org.example.controller.CheckArgumentos;
import org.example.model.tipoCasilleros.Casillero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CheckArgumentos check = new CheckArgumentos();
        check.CheckArgumentos();
        List<String> argumentos = check.getConfiguraciones();
        CrearJugadores jugadores = new CrearJugadores();
        Juego juego = new Juego(jugadores.crearJugadores(argumentos.get(0)));
        JuegoController juegoController = new JuegoController();
        while (!juego.terminado()){
            juegoController.jugarTurno();
        }
        System.out.println("El juego terminado");
    }
    

}




