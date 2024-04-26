package org.example.controller;

import org.example.funciones.FuncionesExtras;
import org.example.model.Comprable;
import org.example.model.Propiedad;
import org.example.model.Jugador;
import org.example.controller.JuegoController;
import org.example.model.Tablero;
import org.example.model.tipoCasilleros.TipoCasillero;

import java.util.List;

public class Deshipotecar implements EjecutarAccion{

    private FuncionesExtras funciones;
    public Deshipotecar(FuncionesExtras func){
        this.funciones = func;
    }
    public void ejecutar(Jugador jugador, int propiedad, ConstruccionController controller) {
        Propiedad prop = funciones.obtenerPropiedadJugador(propiedad,jugador);
        if (prop != null) {
            jugador.deshipotecarPropiedad(prop);
        }
    }

}
