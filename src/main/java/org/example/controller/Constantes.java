package org.example.controller;

public class Constantes {
    public static final int NEGATIVO = -1;

    public static final int PROPIEDADES_POR_BARRIO = 3;
    public static final int PROPIEDADES_MINIMAS = PROPIEDADES_POR_BARRIO + 1;
    public static final int CANTIDAD_CASILLEROS_EXTRA = 2;
    public static final int POS_INICIAL = 0;
    public static final int CANTIDAD_CASILLERO_CARCEL= 1;
    public static final int CANTIDAD_CASILLERO_IR_A_CARCEL= 1;
    public static final int CANTIDAD_CASILLERO_MULTA= 1;
    public static final int CANTIDAD_CASILLERO_LLEGADA_PARTIDA= 1;
    public static final int CANTIDAD_CASILLERO_DE_PASO= 1;
    public static final int CANTIDAD_CASILLERO_DE_LOTERIA= 1;
    public static final int CANTIDAD_MINIMAS_CASILLEROS = CANTIDAD_CASILLEROS_EXTRA + PROPIEDADES_MINIMAS+CANTIDAD_CASILLERO_CARCEL+
            CANTIDAD_CASILLERO_DE_LOTERIA + CANTIDAD_CASILLERO_LLEGADA_PARTIDA + CANTIDAD_CASILLERO_MULTA +CANTIDAD_CASILLERO_DE_PASO +
            CANTIDAD_CASILLERO_IR_A_CARCEL;
    public static final int CANTIDAD_CASILLEROS_NO_PROPIEDADES = CANTIDAD_MINIMAS_CASILLEROS - PROPIEDADES_MINIMAS - CANTIDAD_CASILLEROS_EXTRA;
}
