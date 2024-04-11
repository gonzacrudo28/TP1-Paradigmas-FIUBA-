package org.example.model;

import org.example.controller.Constantes;
import org.example.model.tipoCasilleros.*;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class Tablero {
    private List<Casillero> casilleros;
    public int cantCasilleros;
    private HashMap<TipoPropiedad, Integer> posCasilleros ;

    public Tablero(int numeroCasilleros) {
        this.cantCasilleros = numeroCasilleros;
        this.casilleros = new ArrayList<>();
        this.posCasilleros = new HashMap<>();
        inicializarCasilleros();
    }


    public int getCantidadCasilleros() {
        return this.casilleros.size();
    }

    private void inicializarCasilleros() {
        inicializarCasillerosMinimos();
        int cantCasillasLibres = (cantCasilleros - Constantes.CASILLEROS_MINIMOS);
        int cantBarrios = cantCasilleros/5;
        int cantCasillerosExtra = cantCasillasLibres-cantBarrios*Constantes.CASAS_POR_BARRIO;
        //Falta definir de esos casilleros extra cual es para multa,transporte,loretia

    }
    private void inicializarCasillerosMinimos() {
        posCasilleros.put(TipoPropiedad.LlegadaPartida,0);
        posCasilleros.put(TipoPropiedad.Carcel,cantCasilleros/4);
        posCasilleros.put(TipoPropiedad.IrALaCarcel,cantCasilleros*3/4);
        posCasilleros.put(TipoPropiedad.DePaso,cantCasilleros/2);
    }
}

/*
* TABLERO REQUERIMIENTOS:
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