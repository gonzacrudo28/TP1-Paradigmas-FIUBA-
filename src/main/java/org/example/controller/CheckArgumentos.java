package org.example.controller;

import org.example.controller.ConfiguracionCheckArgumentos;
import org.example.funciones.DelayPrint;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.TerminalBuilder;
import org.jline.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class CheckArgumentos{
    private LineReader reader;
    private List<String> configuraciones;
    private Scanner entrada;
    public CheckArgumentos()  throws IOException {
        Terminal terminal = TerminalBuilder.terminal();
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
        CheckNum checkNum = new CheckNum();
        CheckNombres checkNombres = new CheckNombres();
        DelayPrint.delayPrint("Bienvenidos al Monopoly! Para jugar necesitamos que ingresen los siguientes datos:", 10);
        List<String> argumentos = new ArrayList<>();
        argumentos.add("Nombres (2 a 4 jugadores y separados por espacios)");
        argumentos.add("Cantidad de casilleros (minimo 12)");
        argumentos.add("Numero de caras del dado");
        argumentos.add("Monto de dinero inicial (minimo 100)");
        argumentos.add("Monto de dinero por vuelta");
        argumentos.add("Cantidad de turnos preso");
        argumentos.add("Monto de multa");
        argumentos.add("Monto de fianza");
        List<ConfiguracionCheckArgumentos> configuraciones = Arrays.asList(ConfiguracionCheckArgumentos.values());
        List<String> inputs = new ArrayList<>();
        for (int contador = 0; contador < argumentos.size(); contador++) {
            String string = reader.readLine(argumentos.get(contador));
            if (contador == 0) {
                boolean boole = checkNombres.checkNombres(string);
                while (!boole){
                    string = reader.readLine(argumentos.get(contador));
                    boole = checkNombres.checkNombres(string);
                }
                inputs.add(string);
            }else if (contador == 1){
                boolean boole = checkNum.checkNumeros(string,configuraciones.get(contador));
                while (!boole){
                    string = reader.readLine(argumentos.get(contador));
                    boole = checkNum.checkNumeros(string,configuraciones.get(contador));
                }
                inputs.add(string);
                int numeroDeCantidadDeCasilleros = Integer.parseInt(inputs.get(contador));
                argumentos.set(2, "Numero maximo del dado a tirar (maximo " + numeroDeCantidadDeCasilleros + ")");
            }else if(contador==2) {
                boolean boole = checkNum.checkNumeroMaximoEnDado(inputs.get(contador),configuraciones.get(contador),Integer.parseInt(inputs.get(ConfiguracionCheckArgumentos.CASILLEROS.ordinal())));

                while (!boole){
                    string = reader.readLine(argumentos.get(contador));
                    boole = checkNum.checkNumeroMaximoEnDado(inputs.get(contador),configuraciones.get(contador),Integer.parseInt(inputs.get(ConfiguracionCheckArgumentos.CASILLEROS.ordinal())));

                }
                inputs.add(string);

            }else{
                boolean boole = checkNum.checkNumeros(inputs.get(contador),configuraciones.get(contador));
                while (!boole){
                    string = reader.readLine(argumentos.get(contador));
                    boole = checkNum.checkNumeros(inputs.get(contador),configuraciones.get(contador));
                }
                inputs.add(string);
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
