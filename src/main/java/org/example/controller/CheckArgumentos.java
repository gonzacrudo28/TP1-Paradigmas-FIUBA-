package org.example.controller;

import com.sun.tools.javac.Main;
import org.example.model.AdministradorTurnos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
    public void CheckArgumentos() {
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
                CheckNombres checkNombres = new CheckNombres();
                checkNombres.checkNombres(inputs.get(contador));
            } else {
                CheckNum checkNum = new CheckNum();
                checkNum.checkNumeros(inputs.get(contador));
            }
        }

        sc.close();
        this.configuraciones = inputs;
    }


    public List<String> getConfiguraciones() {
        return this.configuraciones;
    }

}
