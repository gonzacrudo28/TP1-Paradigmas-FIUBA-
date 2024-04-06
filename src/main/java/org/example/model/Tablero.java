package org.example.model;

import org.example.model.tipoCasilleros.Casillero;
import java.util.List;
import java.util.ArrayList;

public class Tablero {
    private List<Casillero> casilleros;
    public int cantCasilleros;

    public Tablero(int numeroCasilleros) {
        this.cantCasilleros = numeroCasilleros;
        this.casilleros = new ArrayList<>();
        inicializarCasilleros();
    }


    public int getCantidadCasilleros() {
        return this.casilleros.size();
    }
    private void inicializarCasilleros() {
    }
}

/*
* TABLERO REQUERIMIENTOS:
*
*   MINIMO DE CASILLEROS (7 VAMOS BOQUITA)
*   MINIMO UNA CARCEL
*   MINIMO UN IR A CARCEL
*   MINIMO UNA PROPIEDAD
*   MINIMO UN SALIDA/LLEGADA
*   MINIMO UNO DE PASO
*   MINIMO UNO DE LOTERIA
*   MINIMO UNO DE MULTA
*
*   POS SALIDA/LLEGADA = 0
*   int numeroMinimoCasillerosNoPropiedades = 6
*   CANT PROPIEDADES = CANT CASILLEROS - numeroMinimoCasillerosNoPropiedades
*   CANT BARRIOS = CANT PROPIEDADES // 3
*   SI EL RESTO NO ES CERO HAGO UN BARRIO CON LAS PROPIEDADES RESTANTES
*
*   CADA 1 BARRIO, UN CASILLERO NO PROPIEDAD (LOTERIA,MULTA, DE PASO)
*   POS IR A CARCEL = CARCEL * 2
*
*   BARRIO DE MAXIMO 3 PROPIEDADES
* */