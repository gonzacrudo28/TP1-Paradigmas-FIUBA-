package org.example.controller;

import org.example.model.Propiedad;
import org.example.model.Tablero;
import org.example.model.tipoCasilleros.DePaso;
import org.example.model.Jugador;
import java.util.ArrayList;

public class TableroController {
    private Tablero tablero;

    public  TableroController(Tablero tablero) {
        this.tablero = tablero;
    }

    public void eliminarPropiedadesDelJugadorEnQuiebra(Jugador jugador){
        ArrayList<Propiedad> propiedades = jugador.getPropiedades();
        for (Propiedad propiedad : propiedades) {
            propiedad.setPropietario(null);
            int ubicacion = propiedad.getUbicacion();
            DePaso NuevoCasilleroDePaso = new DePaso(ubicacion);
            tablero.getTodosLosCasilleros()[ubicacion] =   NuevoCasilleroDePaso;
        }
    }
}


