package org.example.controller;

import org.example.funciones.FuncionesExtras;
import org.example.model.Jugador;
import org.example.model.Tablero;
import org.example.model.Propiedad;
import org.example.model.tipoCasilleros.TipoCasillero;
import org.example.controller.ConstruccionController;


import java.util.List;

public class Construir implements EjecutarAccion{
    private FuncionesExtras funciones;

    public Construir(FuncionesExtras func){
        this.funciones = func;
    }
    public void ejecutar(Jugador jugador, int propiedad, ConstruccionController controller) {
        Propiedad prop = funciones.obtenerPropiedadJugador(propiedad, jugador);
        if (prop != null) {
            controller.construirEnPropiedad(jugador, prop);
        }
    }




}
