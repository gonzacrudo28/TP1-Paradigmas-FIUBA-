package org.example.controller;

import com.sun.tools.javac.Main;
import org.example.model.AdministradorTurnos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CheckArgumentos{
    public enum ConfiguracionCheckArgumentos{
        JUGADORES,
        CASILLEROS,
        CANTIDAD_DE_LADOS_EN_DADO,
        DINERO_INICIAL,
        DINERO_VUELTA,
        TURNOS_PRESO,
        MULTA,
        FIANZA
    }
    private List<String> configuraciones;
    private Scanner entrada;
    public void CheckArgumentos() {
        System.out.println("Bienvenidos al Monopoly! Para jugar necesitamos que ingresen los siguientes datos:");
        List<String> argumentos = new ArrayList<>();
        argumentos.add("Nombres (2 a 4 jugadores y separados por espacios)");
        argumentos.add("Cantidad de casilleros (minimo 10)");
        argumentos.add("Numero de caras del dado");
        argumentos.add("Monto de dinero inicial (minimo 100)");
        argumentos.add("Monto de dinero por vuelta");
        argumentos.add("Cantidad de turnos preso");
        argumentos.add("Monto de multa");
        argumentos.add("Monto de fianza");
        List<ConfiguracionCheckArgumentos> configuraciones = Arrays.asList(ConfiguracionCheckArgumentos.values());
        List<String> inputs = new ArrayList<>();
        this.entrada= new Scanner(System.in);
        //ARREGLAR PRINCIPIO QUE SE ROMPE (TDA/DRY)
        for (int contador = 0; contador < argumentos.size(); contador++) {
            System.out.println(argumentos.get(contador));
            inputs.add(entrada.nextLine());
            if (contador == 0) {
                CheckNombres checkNombres = new CheckNombres();
                checkNombres.checkNombres(inputs.get(contador));
            }else if (contador == 1){
                CheckNum checkNum = new CheckNum();
                checkNum.checkNumerosDeCasilleros(inputs.get(contador),configuraciones.get(contador));
                int numeroDeCantidadDeCasilleros = Integer.parseInt(inputs.get(contador));
                argumentos.set(2, "Numero maximo del dado a tirar (maximo " + numeroDeCantidadDeCasilleros + ")");
            }else if(contador==2) {
                CheckNum checkNum = new CheckNum();
                checkNum.checkNumeroMaximoEnDado(inputs.get(contador),configuraciones.get(contador),Integer.parseInt(inputs.get(ConfiguracionCheckArgumentos.CASILLEROS.ordinal())));
            }else{
                CheckNum checkNum = new CheckNum();
                checkNum.checkNumerosDeCasilleros(inputs.get(contador),configuraciones.get(contador));
            }
        }
        this.configuraciones = inputs;
    }
    public void CerrarScanner(){
        this.entrada.close();
    }
    public List<String> getConfiguraciones() {
        return this.configuraciones;
    }

}
