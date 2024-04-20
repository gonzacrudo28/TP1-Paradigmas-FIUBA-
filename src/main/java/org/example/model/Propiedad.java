package org.example.model;


import com.sun.jdi.event.ExceptionEvent;
import org.example.controller.PropiedadesController;
import org.example.model.Jugador;
import org.example.model.Banco;

import java.util.ArrayList;
import java.util.Map;

import java.util.HashMap;
// Enum EstadoPropiedad

// Clase Propiedad
public class Propiedad {
    protected int precio;
    protected int numeroDeBarrio;
    protected int alquiler;
    protected Jugador propietario;
    protected EstadoPropiedad estado;
    protected Construcciones construcciones;
    protected int ubicacion;

    public enum EstadoPropiedad {
        COMPRADO, EN_VENTA, HIPOTECADO
    }

    public Propiedad(int precio, int numeroDeBarrio,int ubicacion) {
        this.precio = precio;
        this.numeroDeBarrio = numeroDeBarrio;
        this.ubicacion = ubicacion;
        this.propietario = null;
        this.estado = EstadoPropiedad.EN_VENTA;
        this.construcciones = Construcciones.SIN_CASA;
    }




    // Enum Construcciones
    public enum Construcciones {
        SIN_CASA, UNA_CASA, DOS_CASAS, TRES_CASAS, HOTEL;
        private static final Construcciones[] valores = values();
        public Construcciones siguiente() {
            Construcciones siguiente = valores[(this.ordinal() + 1) % valores.length];
            if (siguiente == SIN_CASA) {
                return HOTEL;
            }else{
                return siguiente;
            }
        }
    }




    // Constructor



    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public EstadoPropiedad getEstado() {
        return this.estado;
    }
    public int getPrecio() {
        return precio;
    }
    public double getAlquiler() {
        return alquiler;
    }
    public Jugador getPropietario() { return propietario;}
    public int getBarrio() {
        return numeroDeBarrio;
    }
    public Integer getUbicacion(){return this.ubicacion;}
   public void setPropietario(Jugador propietario) {
        //Top: Rompemos el tell don't ask, pero despues lo solucionamos cuando todo ande
        if(this.propietario == null){
            this.propietario = propietario;
            this.estado = EstadoPropiedad.COMPRADO;
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
        return construccionActual.siguiente();

    }
    public boolean validarPropietario(Jugador propietario){
        return propietario == this.getPropietario();
    }

    public void hipotecar (){
        estado = EstadoPropiedad.HIPOTECADO;
    }

    public void deshipotecar () {
        estado = EstadoPropiedad.COMPRADO;
    }

    public void liberar(){
        this.construcciones = Construcciones.SIN_CASA;
        this.propietario = null;
        this.estado = EstadoPropiedad.EN_VENTA;
    }

    public void mejorarPropiedad(){
        this.construcciones = this.getSiguienteConstruccion(this.construcciones);
    }

    public void vender(){
        // Falta chequear que haya vendido todas las construcciones -G
        double precioReventa = this.precio * 0.8;
        this.propietario.sumarPlata((int)precioReventa);
        this.liberar();
    }
}



