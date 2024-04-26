package org.example.controller;

import org.example.model.tipoCasilleros.Casillero;
import org.example.controller.ConfiguracionCheckArgumentos;

public class CheckNum {
    public boolean checkNumeroMaximoEnDado(String cantidad, ConfiguracionCheckArgumentos config,int cantidadDeCasillerosMaximos) {
        CheckStrToInt checkStrToInt = new CheckStrToInt();
        int numeroPuesto = checkStrToInt.checkStringToInt(cantidad);
        if (numeroPuesto <= 0 ){
            System.out.println("Esa cantidad no alcanza el minimo permitido");
            System.exit(0);
            return false;
        } else if (numeroPuesto > cantidadDeCasillerosMaximos) {
            System.out.println("Esa cantidad excede el maximo permitido");
            System.exit(0);
            return false;
        }else{
            return true;
        }
    }
    public boolean checkNumeros(String cantidad, ConfiguracionCheckArgumentos config) {
        CheckStrToInt checkStrToInt = new CheckStrToInt();
        int numeroPuesto = checkStrToInt.checkStringToInt(cantidad);
        if ((config == ConfiguracionCheckArgumentos.DINERO_INICIAL && numeroPuesto < Constantes.CANTIDAD_INICIAL_MINIMA) || (config == ConfiguracionCheckArgumentos.CASILLEROS && numeroPuesto < Constantes.CANTIDAD_CASILLEROS_MINIMA)){
            System.out.println("Esa cantidad no alcanza el minimo permitido");
            System.exit(0);
            return false;
        }else{
            return true;
        }
        }

}
