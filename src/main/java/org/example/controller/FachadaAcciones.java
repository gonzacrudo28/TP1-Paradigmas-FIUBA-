package org.example.controller;

public class FachadaAcciones implements ListaAcciones{
    private Hipotecar hipoteca;
    private Comprar comprar;
    private Vender vender;
    private ConsultarPrecios consultarPrecios;
    private Construir construir;
    private Deshipotecar deshipotecar;
    private PagarFianza fianza;


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
    public void comprar() {
        comprar.ejecutar();
    }

    @Override
    public void construir() {
        construir.ejecutar();
    }

    @Override
    public void deshipotecar() {
        deshipotecar.ejecutar();
    }

    @Override
    public void hipotecar() {
        hipoteca.ejecutar();
    }

    @Override
    public void consultar_precio_casa() {
        consultarPrecios.ejecutar();
    }

    @Override
    public void terminar_turno() {

    }

    @Override
    public void vender() {
        vender.ejecutar();
    }

    @Override
    public void pagar_fianza() {
        fianza.ejecutar();
    }

}
