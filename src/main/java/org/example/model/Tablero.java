package org.example.model;

import org.example.model.tipoCasilleros.Casillero;
import java.util.List;
import java.util.ArrayList;

public class Tablero {
    private List<Casillero> casilleros;

    public Tablero() {
        this.casilleros = new ArrayList<>();
        inicializarCasilleros();
    }
    public int getCantidadCasilleros() {
        return this.casilleros.size();
    }
    private void inicializarCasilleros() {


    }
}

