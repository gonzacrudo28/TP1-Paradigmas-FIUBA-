package org.example.controller;

import org.example.model.AdministradorTurnos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckArgumentos {
    public CheckArgumentos() {
        System.out.println("Bienvenidos al Monopoly! Para jugar necesitamos que ingresen los siguentes datos:");
        List<String> argumentos = new ArrayList<>();
        argumentos.add("Nombres(2 a 4 jugadores y separados por espacios)");
        argumentos.add("Cantidad de casilleros");
        argumentos.add("Monto de dinero inicial");
        argumentos.add("Cantidad de turnos preso");
        argumentos.add("Monto de multa");
        List<String> inputs = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int contador = 0;
        for (String arg : argumentos){
            System.out.println(arg);
            inputs.add(sc.nextLine());
            if (contador == 0){
                checkNombres(inputs.getFirst());
                contador++;
                AdministradorTurnos()
            }else{
                checkNumeros(inputs.get(contador));
                contador++;
            }

        }
        sc.close();

    }
    public static void checkNombres(String nombre) {
        String[] nombres = nombre.split(" ");
        if (nombres.length < 2 || nombres.length > 4) {
            System.out.println("La cantidad de nombres no es correcta");
            System.exit(0);
        }
    }
    public static void checkNumeros(String cantidad) {
        if (checkStringToInt(cantidad) == -1) {
            System.out.println("Esa cantidad no es posible");
            System.exit(0);
        }
    }


    protected static int checkStringToInt(String str){
        try {
            int num = Integer.parseInt(str);
            if (num > 0){
                return num;
            }else{
                return -1;
            }
        }catch (NumberFormatException e){
            return -1;
        }
    }
}
