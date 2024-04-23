package org.example;


import org.example.controller.Configuracion;
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
        JuegoController juegoController = new JuegoController(juego);

        while (!juego.terminado()){
            juegoController.jugarTurno();
        }
        check.CerrarScanner();
        System.out.println("El juego terminado");
    }
}
/*
COSAS PARA HACER:
    • Agregar delay (se encarga top o intenta) (OPCIONAL)
    • Limitar las acciones(habria que
    • Modularizar JuegoController
    ver si funciona bien la compra de transporte
    • Hacer jugaTurnoView(?
    .comprar(construir) reste precio y validar constucciones
*/


