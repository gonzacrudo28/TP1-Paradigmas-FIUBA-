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
    • Modularizar JuegoController

    • Hacer jugaTurnoView(?
    • ELIMINAR PROPIEDADES DEL JUGADOR EN EL TABLERO CUANDO ENTRA EN QUIEBRA

    NO SE AUMENTA EL PRECIO DEL ALQUILER
    EL PROPIO JUGADOR TIENE Q PAGAR SU ALQUIER
    PERDER
    GANAR
    VERIFICAR TURNO A TURNO SI PERDIO O GANO

*/


