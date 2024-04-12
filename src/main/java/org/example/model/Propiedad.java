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


    public enum EstadoPropiedad {
        COMPRADO, EN_VENTA, HIPOTECADO
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

    protected String nombre;
    protected double precio;
    protected String color;
    protected double alquiler;
    protected Jugador propietario;
    protected EstadoPropiedad estado;
    protected Construcciones construcciones;

    // Constructor
    public Propiedad(String nombre, double precio, String color, double alquiler) {
        this.nombre = nombre;
        this.precio = precio;
        this.color = color;
        this.alquiler = alquiler;
        this.propietario = null;
        this.estado = EstadoPropiedad.EN_VENTA;
        this.construcciones = Construcciones.SIN_CASA;
    }


    public EstadoPropiedad getEstado() {
        return this.estado;
    }
    public double getPrecio() {
        return precio;
    }
    public double getAlquiler() {
        return alquiler;
    }
    public Jugador getPropietario() {return propietario;}
    public String getColor() {return this.color;}
    public String getNombre() {return this.nombre;}

   public boolean setPropietario(Jugador propietario) {
        if (propietario.restarPlata((int)this.getPrecio())){
           this.propietario = propietario;
           this.estado = EstadoPropiedad.COMPRADO;
           //PropiedadesController.actualizarPropiedad(this);
        }
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

    public void deshipotecar (){
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
        // Falta chequear que haya vendido todas las construcciones y que sea el dueño, -G
        double precioReventa = this.precio * 0.8;
        this.propietario.sumarPlata((int)precioReventa);
        this.liberar();
    }
}



