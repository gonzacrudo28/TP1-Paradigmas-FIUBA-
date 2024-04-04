package org.example;


import org.example.model.Propiedad;
import org.example.model.Estacion;
import org.example.controller.CheckArgumentos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public void crearJuego() {
        CheckArgumentos check = new CheckArgumentos();
        List<String> argumentos = check.getConfiguraciones();

    }
}


/*
* public static void main(String[] args) {
        CheckArgumentos check = new CheckArgumentos();
        List<String> coonfi= check.getInputs();
        System.out.println(coonfi);

        String[] configArray = coonfi.toArray(new String[0]);
        int montoInicial = Integer.parseInt(configArray[Configuracion.DINERO_INICIAL.ordinal()]);
        System.out.println(configArray[Configuracion.MULTA.ordinal()]);
        System.out.println("el monto inicial es: "+montoInicial);*/