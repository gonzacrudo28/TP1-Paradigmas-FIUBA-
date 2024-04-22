package org.example.model;


import com.sun.jdi.event.ExceptionEvent;
import org.example.controller.PropiedadesController;
import org.example.model.Jugador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import java.util.HashMap;
// Enum EstadoPropiedad

// Clase Propiedad
public class Propiedad extends Comprable {

    protected int numeroDeBarrio;
    protected Construcciones construcciones;

    /* =ATRIBUTOS QUE COMPRABLE
    protected int ubicacion;
    protected int precio;
    protected int alquiler;
    protected Jugador propietario;
    protected EstadoPropiedad estado;
    */

    public Propiedad(double precio, int numeroDeBarrio,int ubicacion) {
        super(precio, ubicacion);
        this.numeroDeBarrio = numeroDeBarrio;
        this.construcciones = Construcciones.SIN_CASA;

    }
        /*
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.propietario = null;
        this.estado = EstadoPropiedad.EN_VENTA;
        */

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
/*
Hice una abstract class llamada comprable que la heredan propiedad y EstacionTransporte
Como las estaciones son comprables y tienen muchas cosas en comun y algunos metodos con != implementaciones
entonces hice una superclase abstracta para DRY y modularizar. faq

    public void setUbicacion(int ubicacion) { this.ubicacion = ubicacion;}
    public EstadoPropiedad getEstado() {return this.estado; }
    public int getPrecio() { return precio; }
    public double getAlquiler() { return alquiler; }
    public Jugador getPropietario() { return propietario;}

    public int getUbicacion(){return this.ubicacion;}
*/
    public int getBarrio() { return numeroDeBarrio; }

   public void setPropietario(Jugador propietario) {
        //Top: Rompemos el tell don't ask, pero despues lo solucionamos cuando todo ande
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
        System.out.println("Propiedad mejorada a "+this.getConstrucciones()+" con exito");
    }

    public void vender(){
        // Falta chequear que haya vendido todas las construcciones -G
        double precioReventa = this.precio * 0.8;
        this.propietario.sumarPlata((int)precioReventa);
        this.liberar();
    }
}



