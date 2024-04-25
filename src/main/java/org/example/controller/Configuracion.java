package org.example.controller;


import org.example.model.Colores;
import org.example.model.Jugador;
import org.example.controller.ConfiguracionCheckArgumentos;
import org.fusesource.jansi.Ansi;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.example.funciones.FuncionColorPrints;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;


public class Configuracion {
    private int montoVuelta;
    private int montoInicial;
    private int cantidadDeLados;
    private int condenaCarcel;
    private final int montoDeMulta;
    private int cantidadCasilleros;
    private int montoFianza;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Colores> colores;


    public Configuracion(List<String> configuraciones) {
        cantidadCasilleros = Integer.parseInt(configuraciones.get(ConfiguracionCheckArgumentos.CASILLEROS.ordinal()));
        montoInicial = Integer.parseInt(configuraciones.get(ConfiguracionCheckArgumentos.DINERO_INICIAL.ordinal()));
        System.out.println(montoInicial);
        cantidadDeLados= Integer.parseInt(configuraciones.get(ConfiguracionCheckArgumentos.CANTIDAD_DE_LADOS_EN_DADO.ordinal()));
        montoVuelta = Integer.parseInt(configuraciones.get(ConfiguracionCheckArgumentos.DINERO_VUELTA.ordinal()));
        condenaCarcel = Integer.parseInt(configuraciones.get(ConfiguracionCheckArgumentos.TURNOS_PRESO.ordinal()));
        montoDeMulta = Integer.parseInt(configuraciones.get(ConfiguracionCheckArgumentos.MULTA.ordinal()));
        montoFianza = Integer.parseInt(configuraciones.get(ConfiguracionCheckArgumentos.FIANZA.ordinal()));
        ArrayList<Jugador> jugadores = this.crearJugadores(configuraciones);
        this.jugadores = jugadores;
        asignacionColores(jugadores);
    }

    private ArrayList<Jugador> crearJugadores(List<String> configuraciones){
        List<String> nombres = List.of(configuraciones.get(0).split(" "));
        int plataInicial = Integer.parseInt(configuraciones.get(ConfiguracionCheckArgumentos.DINERO_INICIAL.ordinal()));
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        for (String nombre : nombres) {
            Jugador jugador = new Jugador(nombre);
            jugador.setPlata(plataInicial);
            jugadores.add(jugador);
        }

        return jugadores;
    }

    private void asignacionColores(List<Jugador> jugadores){
        FuncionColorPrints funcionColorPrints = new FuncionColorPrints();
        Colores.Color[] colores = Colores.Color.values();
        for(int i = 0; i < jugadores.size(); i++){
            System.out.println("El jugador " + jugadores.get(i).getNombre() + " debe elegir el color: ");
            int opcionColor=this.elegirColor(colores);
            jugadores.get(i).setColor(colores[opcionColor-1]);
            colores[opcionColor -1] = null;
        }
    }

    private int validarIngresoColor(Colores.Color[] colores, int opcion){
        while(opcion <1 || opcion > colores.length || colores[opcion-1] ==null){
            System.out.println("ERROR: NUMERO NO VALIDO");
            opcion = elegirColor(colores);
        }
        return opcion;
    }
    private int  elegirColor(Colores.Color[] colores ){
        FuncionColorPrints funcionColorPrints = new FuncionColorPrints();
        LineReader reader = LineReaderBuilder.builder().build();
        System.out.println("Los colores disponibles son:");
        for (int i= 0; i < colores.length; i++){
            if (colores[i]!= null){
                Ansi colorANSI = funcionColorPrints.obtenerColorANSI(colores[i]);
                Ansi resetColor = Ansi.ansi().reset();
                System.out.println(i+1 +") Color: " + colorANSI+ colores[i] + resetColor);

            }
        }
        String opcion = reader.readLine("Ingrese el numero de color que desea elegir: ");
        int opcionInt = Integer.parseInt(opcion);
        opcionInt = validarIngresoColor(colores, opcionInt);
        return opcionInt;
    }


    public double getMontoVuelta() {
        return montoVuelta;
    }

    public double getMontoInicial(){
        return (double) montoInicial;
    }

    public ArrayList<Jugador> getJugadores() { return jugadores;}

    public double getMontoFianza() { return  montoFianza; }

    public int getCantidadCasilleros() { return cantidadCasilleros; }

    public int getCondenaCarcel(){return condenaCarcel;}

    public double getMontoMulta(){return montoDeMulta;}

    public int getCantidadDeLadosEnDado(){ return cantidadDeLados;}
}
