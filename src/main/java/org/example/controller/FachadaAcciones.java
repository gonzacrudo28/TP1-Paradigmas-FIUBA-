package org.example.controller;

import org.example.model.Jugador;
import org.example.model.Tablero;

public class FachadaAcciones implements ListaAcciones{
    private Hipotecar hipoteca;
    private Comprar comprar;
    private Vender vender;
    private ConsultarPrecios consultarPrecios;
    private Construir construir;
    private Deshipotecar deshipotecar;
    private PagarFianza fianza;

    private JuegoController juego;



public FachadaAcciones(Hipotecar hipoteca,Comprar comprar,Vender vender,ConsultarPrecios consultarPrecios,Construir construir,Deshipotecar deshipotecar,PagarFianza fianza) {
    this.hipoteca = hipoteca;
    this.comprar = comprar;
    this.vender = vender;
    this.consultarPrecios = consultarPrecios;
    this.construir = construir;
    this.deshipotecar = deshipotecar;
    this.fianza = fianza;
}
    @Override
    public void comprar(Jugador jugador,int num,Tablero tablero,  ConstruccionController controller) {
        comprar.ejecutar(jugador,num,tablero,controller);
    }
    @Override
    public void construir(Jugador jugador, int propiedad, Tablero tablero,  ConstruccionController controller) {
        construir.ejecutar(jugador,propiedad,tablero,controller);
    }

    @Override
    public void deshipotecar(Jugador jugador, int propiedad, Tablero tablero, ConstruccionController controller) {
        deshipotecar.ejecutar(jugador,propiedad,tablero,controller);
    }

    @Override
    public void hipotecar(Jugador jugador, int propiedad, Tablero tablero, ConstruccionController controller) {
        hipoteca.ejecutar(jugador,propiedad,tablero,controller);
    }

    @Override
    public void consultar_precio_casa(Jugador jugador, int propiedad, Tablero tablero,  ConstruccionController controller) {
        consultarPrecios.ejecutar(jugador,propiedad,tablero,controller);
    }

    @Override
    public void vender(Jugador jugador, int propiedad, Tablero tablero,  ConstruccionController controller) {
        vender.ejecutar(jugador,propiedad,tablero,controller);
    }

    @Override
    public void pagar_fianza(Jugador jugador, int montoFianza, Tablero tablero,  ConstruccionController controller) {
        fianza.ejecutar(jugador,montoFianza,tablero,controller);
    }

}
