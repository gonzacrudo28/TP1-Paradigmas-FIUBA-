package org.example.controller;

import java.util.ArrayList
import org.example.model.Colores;
import org.example.model.Tablero;
import java.util.Random;


public class Configuracion {
    private int montoPorPasarSalida;
    private int montoInicial;
    private Tablero tablero;
    private List<Colores.Color> colores;
    private int condenaCarcel;
    private int montoDeMulta;

    public Configuracion(List<Colores.Color> colores, int turnosCondena) {
        this.colores = colores;
        condenaCarcel = turnosCondena;
    }

    public void setMontos(int saldoInicial, int saldoLlegada, int montoMulta){
         this.montoInicial = saldoInicial;
         this.montoPorPasarSalida = saldoLlegada;
         this.montoDeMulta = montoMulta;
    }


    public Tablero getTablero(Tablero tablero){
        return this.tablero;
    }

}
