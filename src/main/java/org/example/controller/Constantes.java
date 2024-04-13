package org.example.controller;

public class Constantes {
    public static final int NEGATIVO = -1;
    public static final int POS_LLEGADA_SALIDA = 0;
    public static final double POS_CARCEL = 0.250;
    public static final double POS_DE_PASO = 0.500;
    public static final double POS_IR_CARCEL = 0.750;
    public static final int CANTIDAD_CASILLEROS_BASE= 4;
    public static final double PORCENTAJE_DE_TRANSPORTE= 0.1;
    public static final int CANTIDAD_CASAS_POR_BARRIO = 3;


}


/*25
* 25-4 = 21(CASILLEROS LIBRES)
* 21/3 = 7(CANTIDAD CASILLEROS EXTRAS)
* (21-7)/3 = 4 (BARIOS)
*25-7-12-4 = 2 (SUMO EL RESTO A EXTRAS)
*
* L/G = 0
* CARCEL = 1/4
* ESTACIONAMIENTO = 1/2
* IR CARCEL = 3/4
*
*
* *
*C/L _ _ _ ex ex C _ _ _ ex ex E _ _ _ ex ex IC _ _ _ ex ex _
*/