package org.example.controller;

import org.example.model.Jugador;
import org.example.model.Tablero;

public interface EjecutarAccion {
    void ejecutar(Jugador jugador, int propiedad, Tablero tablero,  ConstruccionController controller);
}
