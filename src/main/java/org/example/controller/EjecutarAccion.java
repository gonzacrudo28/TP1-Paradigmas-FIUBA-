package org.example.controller;

import org.example.model.Jugador;

public interface EjecutarAccion {
    void ejecutar(Jugador jugador, int propiedad,ConstruccionController controller);
}
