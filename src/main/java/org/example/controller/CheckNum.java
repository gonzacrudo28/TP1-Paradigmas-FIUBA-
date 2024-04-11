package org.example.controller;

import org.example.model.tipoCasilleros.Casillero;

public class CheckNum {
public void checkNumeros(String cantidad, CheckArgumentos.ConfiguracionCheckArgumentos config) {
    CheckStrToInt checkStrToInt = new CheckStrToInt();
    boolean bool = checkStrToInt.checkStringToInt(cantidad);
    if (!bool){
        System.out.println("Esa cantidad no es posible");
        System.exit(0);
    } else if (config == CheckArgumentos.ConfiguracionCheckArgumentos.CASILLEROS && Constantes.CANT_MINIMAS_CASILLEROS > Integer.parseInt(cantidad)) {
        System.out.println("Esa cantidad no es posible");
        System.exit(0);
    }else if (Integer.parseInt(cantidad) < 1) {
        System.out.println("Esa cantidad no es posible");
        System.exit(0);
    }
}
}