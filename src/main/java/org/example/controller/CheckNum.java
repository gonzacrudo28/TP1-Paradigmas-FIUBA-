package org.example.controller;

import org.example.model.tipoCasilleros.Casillero;

public class CheckNum {
    public void checkNumerosDeCasilleros(String cantidad, CheckArgumentos.ConfiguracionCheckArgumentos config) {
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

    public void checkNumeroMaximoEnDado(String cantidad, CheckArgumentos.ConfiguracionCheckArgumentos config,int cantidadDeCasillerosMaximos) {
        CheckStrToInt checkStrToInt = new CheckStrToInt();
        int numeroPuesto = checkStrToInt.checkStringToInt(cantidad);
        if (numeroPuesto <= 0 ){
            System.out.println("Esa cantidad no alcanza el minimo permitido");
            System.exit(0);
        } else if (numeroPuesto > cantidadDeCasillerosMaximos) {
            System.out.println("Esa cantidad excede el maximo permitido");
            System.exit(0);
        }
    }
}