package org.example.controller;

import org.example.model.Jugador;
import org.example.model.Tablero;

public class PagarFianza implements EjecutarAccion{
    @Override
    public void ejecutar(Jugador jugador, int montoFianza, Tablero tablero, ConstruccionController controller){
        jugador.pagarFianza(montoFianza);
    }
}
