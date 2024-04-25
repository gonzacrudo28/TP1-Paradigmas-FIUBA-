package org.example.model;


import org.example.controller.CheckHipotecar;
import org.example.controller.Constantes;
import org.example.controller.ConstruccionController;
import org.example.model.Construcciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// Enum EstadoPropiedad

// Clase Propiedad
public class Propiedad extends Comprable {

    private int numeroDeBarrio;
    private Construcciones construcciones;
    private double precioCasa;



    public Propiedad(int precio, int numeroDeBarrio,int ubicacion) {
        super(precio, ubicacion);
        this.numeroDeBarrio = numeroDeBarrio;
        this.construcciones = Construcciones.SIN_CASA;
        this.precioCasa = (precio*Constantes.PORCENTAJE_PRECIO_CASA);
    }


    public double getPrecioCasa() {
        return precioCasa;
    }


    public String getNombrePropietario(){
        return propietario.getNombre();
    }
    public int getBarrio() { return numeroDeBarrio; }

   public void setPropietario(Jugador propietario) {
        if(this.propietario == null){
            this.propietario = propietario;
            this.estado = EstadoPropiedades.COMPRADO;
            propietario.agregarPropiedad(this);
            System.out.println("Propiedad comprada con exito");
            propietario.restarPlata(precio);
            return;
        }
        System.out.println("La propiedad ya fue comprada");
    }

    public Construcciones getConstrucciones(){
        return this.construcciones;
    }

    private Construcciones getSiguienteConstruccion(Construcciones construccionActual) {
        if (construcciones == Construcciones.HOTEL) {
            return Construcciones.HOTEL;
        }else {
            return siguiente(construccionActual);
        }
        }
    private Construcciones getAnteriorConstruccion(Construcciones construccionActual){
        return anterior(construccionActual);
    }
    public Construcciones anterior(Construcciones construccionActual){
        List<Construcciones> construccionesLista = Arrays.asList(Construcciones.values());
        return construccionesLista.get(construccionesLista.indexOf(construccionActual)-1);
    }
    public Construcciones siguiente(Construcciones construccionActual){
        List<Construcciones> construccionesLista = Arrays.asList(Construcciones.values());
        return construccionesLista.get(construccionesLista.indexOf(construccionActual)+1);
    }

    public boolean validarPropietario(Jugador propietario){
        return propietario == this.getPropietario();
    }

    public void hipotecar (Barrio barrio,Jugador jugador){
        CheckHipotecar controladorHipoteca = new CheckHipotecar(jugador,barrio,this);
        if (controladorHipoteca.validarHipotecar()) {
            ponerEnHipoteca();
            jugador.sumarPlata(this.getPrecio());
        }
    }
    public void ponerEnHipoteca(){
        estado = EstadoPropiedades.HIPOTECADO;
    }
    public void deshipotecar () {
        estado = EstadoPropiedades.COMPRADO;
    }

    public void liberar(){
        this.construcciones = Construcciones.SIN_CASA;
        this.propietario = null;
        this.estado = EstadoPropiedades.EN_VENTA;
        this.alquiler = setPrecioBaseAlquiler();
    }

    public void mejorarPropiedad(Barrio barrio, Jugador jugador){
        ConstruccionController construccionController = new ConstruccionController(this,barrio);
        if (construccionController.validarConstruccion(jugador, this)){
            this.construcciones = this.getSiguienteConstruccion(this.construcciones);
            actualizarAlquiler();
            jugador.restarPlata(this.getPrecioCasa());
            jugador.sumarAlPatrimonio(this.getPrecioCasa());
            System.out.println("Propiedad mejorada a "+this.getConstrucciones()+" con exito");
        }
    }

    public void deconstruite(Barrio barrio, Jugador jugador){
        ConstruccionController construccionController = new ConstruccionController(this,barrio);
        if (construccionController.validarVenta(jugador, this)){
            jugador.restarPatrimonio(this.getPrecioCasa());
            this.construcciones = this.getAnteriorConstruccion(this.construcciones);
            actualizarAlquiler();
        }
    }

    public void vender(){
        double precioReventa = this.precio * Constantes.PORCENTAJE_DE_VENTA;
        double plataVentaCasas = venderPropiedades();
        this.propietario.sumarPlata(precioReventa + plataVentaCasas);
        this.liberar();
    }


    public void VenderPropiedad(){
        if(getConstrucciones()==Construcciones.SIN_CASA){
            this.propietario.sumarPlata(getPrecio());
            liberar();
            return;
        }
        System.out.println("No se puede vender la propiedad (tiene casas/hotel)");
    }

    public void venderCasa(Jugador jugador, Barrio barrio){
        double precioReventa = this.precio * Constantes.PORCENTAJE_DE_VENTA;
        this.propietario.sumarPlata(precioReventa);
        this.liberarCasa(jugador, barrio);
    }

    public void liberarCasa(Jugador jugador, Barrio barrio){
        deconstruite(barrio, jugador);
        this.alquiler = setPrecioBaseAlquiler();
    }

    private void actualizarAlquiler(){
        this.alquiler = (int)(alquiler*Constantes.PORCENTAJE_ALQUILER_NUEVA_CASA) + alquiler;
    }

    private double venderPropiedades(){
        List<Construcciones> tiposConstrucciones = Arrays.asList(Construcciones.values());
        double cantPropiedades = tiposConstrucciones.indexOf(construcciones);
        return cantPropiedades*precioCasa;
    }

    private int setPrecioBaseAlquiler(){
        return (int)(precio*Constantes.PORCENTAJE_ALQUILER);
    }

    public void restarAlquiler(Jugador jugador){
        if (jugador == propietario){
            return;
        } else if (this.estado.equals(EstadoPropiedades.COMPRADO)) {
            double parsed = (double)(alquiler);
            jugador.restarPlata(parsed);
            propietario.sumarPlata(parsed);
            System.out.printf("%s acaba de pagar %.2f por el alquiler de la propiedad a %s", jugador.getNombre(), parsed, propietario.getNombre());
        }
    }
}



