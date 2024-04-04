package org.example.controller;

import com.sun.tools.javac.Main;
import org.example.model.AdministradorTurnos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;








/*
* public static class CheckArgumentos {
        private List<String> configuraciones;

        public CheckArgumentos() {
            System.out.println("Bienvenidos al Monopoly! Para jugar necesitamos que ingresen los siguientes datos:");
            List<String> argumentos = new ArrayList<>();
            argumentos.add("Nombres(2 a 4 jugadores y separados por espacios)");
            argumentos.add("Cantidad de casilleros");
            argumentos.add("Monto de dinero inicial");
            argumentos.add("Monto de dinero por vuelta");
            argumentos.add("Cantidad de turnos preso");
            argumentos.add("Monto de multa");

            List<String> inputs = new ArrayList<>();
            Scanner sc = new Scanner(System.in);

            for (int contador = 0; contador < argumentos.size(); contador++) {
                System.out.println(argumentos.get(contador));
                inputs.add(sc.nextLine());

                if (contador == 0) {
                    checkNombres(inputs.get(contador));
                } else {
                    checkNumeros(inputs.get(contador));
                }
            }

            sc.close();
            this.configuraciones = inputs;
        }

        public List<String> getInputs() {
            return this.configuraciones;
        }
    *
    * public static void main(String[] args) {
        CheckArgumentos check = new CheckArgumentos();
        List<String> coonfi= check.getInputs();
        System.out.println(coonfi);

        String[] configArray = coonfi.toArray(new String[0]);
        int montoInicial = Integer.parseInt(configArray[Configuracion.DINERO_INICIAL.ordinal()]);
        System.out.println(configArray[Configuracion.MULTA.ordinal()]);
        System.out.println("el monto inicial es: "+montoInicial);


    }
* */








public class CheckArgumentos{
    public enum Configuracion{
        JUGADORES,
        CASILLEROS,
        DINERO_INICIAL,
        DINERO_VUELTA,
        TURNOS_PRESO,
        MULTA
    }
    private List<String> configuraciones;
    public CheckArgumentos() {
        System.out.println("Bienvenidos al Monopoly! Para jugar necesitamos que ingresen los siguentes datos:");
        List<String> argumentos = new ArrayList<>();
        argumentos.add("Nombres(2 a 4 jugadores y separados por espacios)");
        argumentos.add("Cantidad de casilleros");
        argumentos.add("Monto de dinero inicial");
        argumentos.add("Monto de dinero por vuelta");
        argumentos.add("Cantidad de turnos preso");
        argumentos.add("Monto de multa");
        List<String> inputs = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int contador = 0;
        String[] jugadores;
        for (String arg : argumentos) {
            System.out.println(arg);
            inputs.add(sc.nextLine());
            if (contador == 0) {
                assert nombres != null;
                nombres.add(checkNombres(inputs.getFirst()));
            } else {
                parametros.add(checkNumeros(inputs.get(contador)));
            }
            contador++;
        }
        sc.close();
    }

    public static String[] checkNombres(String nombre) {
        String[] nombres = nombre.split(" ");
        if (nombres.length < 2 || nombres.length > 4) {
            System.out.println("La cantidad de nombres no es correcta");
            System.exit(0);
        }
        return (nombres);
    }
    public static int checkNumeros(String cantidad) {
        int numero  = Integer.parseInt(cantidad);
        if (numero == -1) {
            System.out.println("Esa cantidad no es posible");
            System.exit(0);
        }
        return numero;
    }

    public List<String> getConfiguraciones() {
        return configuraciones;
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
