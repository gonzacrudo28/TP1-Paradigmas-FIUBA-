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
COSAS PARA HACER Pd NICO
SETEAR PRECIOS DE PROPIEDADES EN BASE A LA PLATA INICIAL QUE PONE EL PIBE (SE DEBERIA HACER CUANDO SE CREA EL TABLERO)
EL TABLERO RECIBE ESA INFO precioMulta,int precioVuelta,int turnosPreso PARA QUE SETEE ESOS CASILLEROS CON EL VALOR
IMPRIMIR MAS LINDO EL TABLERO (BASTA QUE EL NOMBRE DEL JUGADOR QUE ESTE EN EL CASILLERO SE PONGA DEL COLOR QUE ES)
ver el tema de las impresiones se imprimen dos veces el estado de los jugadores por turno, dejar solo el de despues de que se tira el dado

*/


