package org.example.model;


import org.example.controller.Constantes;
import org.example.controller.ConstruccionController;

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

    public enum Construcciones {
        SIN_CASA, UNA_CASA, DOS_CASAS, TRES_CASAS, HOTEL;
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
            this.estado = EstadoPropiedad.COMPRADO;
            propietario.agregarPropiedad(this);
            System.out.println("Propiedad comprada con exito");
            propietario.restarPlata((int)precio);
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
    punlic Construcciones anterior(Construcciones construccionActual)
    public Construcciones siguiente(Construcciones construccionActual){
        List<Construcciones> construccionesLista = Arrays.asList(Construcciones.values());
        return construccionesLista.get(construccionesLista.indexOf(construccionActual)+1);
    }

    public boolean validarPropietario(Jugador propietario){
        return propietario == this.getPropietario();
    }

    public void hipotecar (Barrio barrio){
        if (this.construcciones != Construcciones.SIN_CASA) {
            vender();
        }
        estado = EstadoPropiedad.HIPOTECADO;
    }

    public void deshipotecar () {
        estado = EstadoPropiedad.COMPRADO;
    }

    public void liberar(){
        this.construcciones = Construcciones.SIN_CASA;
        this.propietario = null;
        this.estado = EstadoPropiedad.EN_VENTA;
        this.alquiler = setPrecioBaseAlquiler();
    }



    public void mejorarPropiedad(Barrio barrio, Jugador jugador){
        ConstruccionController construccionController = new ConstruccionController(this,barrio);

        if (construccionController.validarConstruccion(jugador, this)){
            this.construcciones = this.getSiguienteConstruccion(this.construcciones);
            actualizarAlquiler();
            System.out.println("Propiedad mejorada a "+this.getConstrucciones()+" con exito");
        }
    }

    public void deconstruite(Barrio barrio, Jugador jugador){
        if(this.construcciones.equals(Construcciones.SIN_CASA)){
            return;
        }
        ConstruccionController construccionController = new ConstruccionController(this,barrio);
        if (construccionController.validarConstruccion(jugador, this)){
            this.construcciones = this.getAnteriorConstruccion(this.construcciones);
            actualizarAlquiler();
            System.out.println("Propiedad mejorada a "+this.getConstrucciones()+" con exito");

        }
    }

    public void vender(){
        double precioReventa = this.precio * Constantes.PORCENTAJE_DE_VENTA;
        double plataVentaCasas = venderPropiedades();
        this.propietario.sumarPlata(precioReventa + plataVentaCasas);
        this.liberar();
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
        } else if (this.estado.equals(EstadoPropiedad.COMPRADO)) {
            double parsed = (double)(alquiler);
            jugador.restarPlata(parsed);
            propietario.sumarPlata(parsed);
            System.out.printf("%s acaba de pagar %.2f por el alquiler de la propiedad a %s", jugador.getNombre(), parsed, propietario.getNombre());
        }
    }
}



