package org.example.controller;

import org.example.model.Jugador;
import org.example.model.tipoCasilleros.Casillero;

public class FachadaAcciones implements ListaAcciones{
    private Hipotecar hipoteca;
    private Comprar comprar;
    private Vender vender;
    private ConsultarPrecios consultarPrecios;
    private Construir construir;
    private Deshipotecar deshipotecar;
    private PagarFianza pagarFianza;

    public FachadaAcciones(Hipotecar hipoteca,Comprar comprar,Vender vender,ConsultarPrecios consultarPrecios,Construir construir,Deshipotecar deshipotecar,PagarFianza pagarFianza) {
        this.hipoteca = hipoteca;
        this.comprar = comprar;
        this.vender = vender;
        this.consultarPrecios = consultarPrecios;
        this.construir = construir;
        this.deshipotecar = deshipotecar;
        this.pagarFianza = pagarFianza;
    }

    @Override
    public void comprar(Jugador jugador,int num,ConstruccionController controller) {
        comprar.ejecutar(jugador,num,controller);
    }

    @Override
    public void construir(Jugador jugador, int propiedad, ConstruccionController controller) {
        construir.ejecutar(jugador,propiedad,controller);
    }

    @Override
    public void deshipotecar(Jugador jugador, int propiedad, ConstruccionController controller) {
        deshipotecar.ejecutar(jugador,propiedad,controller);
    }

    @Override
    public void hipotecar(Jugador jugador, int propiedad, ConstruccionController controller) {
        hipoteca.ejecutar(jugador,propiedad,controller);
    }

    @Override
    public void consultar_precio_casa(Jugador jugador, int propiedad,  ConstruccionController controller) {
        consultarPrecios.ejecutar(jugador,propiedad,controller);
    }

    @Override
    public void vender(Jugador jugador, int propiedad,  ConstruccionController controller) {
        vender.ejecutar(jugador,propiedad,controller);
    }

    @Override
    public void pagar_fianza(Jugador jugador,Casillero carcel){
        pagarFianza.ejecutar(jugador,carcel);
    }
}
