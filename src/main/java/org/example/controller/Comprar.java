package org.example.controller;

import org.example.funciones.FuncionesExtras;
import org.example.model.Comprable;
import org.example.model.Jugador;
import org.example.model.Tablero;
import org.example.model.tipoCasilleros.TipoCasillero;
import org.example.model.tipoCasilleros.DePropiedad;
import org.example.model.tipoCasilleros.Estacion;

public class Comprar implements EjecutarAccion{

    private FuncionesExtras funcionesExtras;

    public Comprar(FuncionesExtras func){
        this.funcionesExtras = func;
    }

    public void ejecutar(Jugador jugador, int propiedad, ConstruccionController controller) {
        int ubicacionJugador = jugador.getUbicacion();
        if (funcionesExtras.esComprable(ubicacionJugador)) {
            Comprable comprable = funcionesExtras.obtenerComprable(ubicacionJugador);
            if (comprable.getPropietario() != null) {
                jugador.comprarComprable(comprable);
            }
        }
    }
    }
