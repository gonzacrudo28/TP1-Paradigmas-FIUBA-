package org.example;
import org.example.controller.JuegoController;
import org.example.model.*;
import org.example.controller.CheckArgumentos;
import org.example.view.JuegoView;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        CheckArgumentos check = new CheckArgumentos();
        List<String> argumentos = check.getConfiguraciones();
        Juego juego = new Juego(argumentos);
        JuegoController juegoController = new JuegoController(juego);
        juego.addObserver(juegoController);
        JuegoView juegoView = new JuegoView(juego);


        while (!juego.terminado()) {
            juegoController.jugarTurno();
        }
        juegoView.terminarJuego();

    }
}
/*Tirar los dados antes de empezar a jugar, o sea que lo primero que muestre es cuanto saco*/


