package org.example.controller;

import org.example.model.tipoCasilleros.Casillero;

public class CheckNum {
public void checkNumeros(String cantidad, CheckArgumentos.ConfiguracionCheckArgumentos config) {
    CheckStrToInt checkStrToInt = new CheckStrToInt();
    int numero = checkStrToInt.checkStringToInt(cantidad);
    if (numero <= 0 ){
        System.out.println("Esa cantidad no es posible");
        System.exit(0);
    } else if (config == CheckArgumentos.ConfiguracionCheckArgumentos.CASILLEROS && Constantes.CANTIDAD_CASILLEROS_BASE > numero) {
        System.out.println("Esa cantidad no es posible");
        System.exit(0);
    }
}
}