package org.example.controller;

import org.example.model.Colores;
import org.example.model.Tablero;
import java.util.List;


public class Configuracion {
    private int montoPorPasarSalida;
    private int montoInicial;
    public Tablero tablero;
    private List<Colores.Color> colores;
    private int condenaCarcel;
    private int montoDeMulta;

    public Configuracion(List <Colores.Color> colores) {
        this.colores = colores;
    }

    public void setTablero(Tablero tablero) { this.tablero = tablero; }

    public void setCondenaCarcel(int condenaCarcel) {
        this.condenaCarcel = condenaCarcel;
    }

    public void setMontos(int saldoInicial, int saldoLlegada, int montoMulta){
         this.montoInicial = saldoInicial;
         this.montoPorPasarSalida = saldoLlegada;
         this.montoDeMulta = montoMulta;
    }

    public int getMontoPorPasarSalida(){
        return montoPorPasarSalida;
    }


    public Tablero getTablero(){
        return this.tablero;
    }

}
