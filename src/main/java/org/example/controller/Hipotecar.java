package org.example.controller;

import org.example.model.Propiedad;
import org.example.model.Jugador;
import org.example.model.Tablero;
import org.example.controller.JuegoController;
import org.example.model.tipoCasilleros.TipoCasillero;
import org.example.funciones.FuncionesExtras;
import java.util.List;

public class Hipotecar implements  EjecutarAccion{
    private FuncionesExtras funcionesExtras;

    public Hipotecar(FuncionesExtras func){
        this.funcionesExtras = func;
    }

    public void ejecutar(Jugador jugador, int propiedad, ConstruccionController controller) {
        Propiedad prop = funcionesExtras.obtenerPropiedadJugador(propiedad,jugador);
        if (prop != null) {
            Tablero tablero = funcionesExtras.getTablero();
            jugador.hipotecarPropiedad(tablero.getBarrio(prop),prop);

        }
    }



}
