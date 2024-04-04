package org.example.model;


import com.sun.jdi.event.ExceptionEvent;
import org.example.model.Jugador;
import org.example.model.Banco;
import Java.util.Hashmap;
import Java.util.Map;

import java.util.HashMap;
// Enum EstadoPropiedad

// Clase Propiedad
public class Propiedad {


    public enum EstadoPropiedad {
        COMPRADO, EN_VENTA, HIPOTECADO
    }

    // Enum Construcciones
    public enum Construcciones {
        SIN_CASA, UNA_CASA, DOS_CASAS, TRES_CASAS, CUATRO_CASAS, HOTEL;
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
    private HashMap<String, List<Propiedades>> DiccionarioProps ;

    // Constructor
    public Propiedad(String nombre, double precio, String color, double alquiler) {
        this.nombre = nombre;
        this.precio = precio;
        this.color = color;
        this.alquiler = alquiler;
        this.propietario = null;
        this.estado = EstadoPropiedad.EN_VENTA;
        this.construcciones = Construcciones.SIN_CASA;
        this.DiccionarioProps = new HashMap<String, List<Propiedades>>();
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

    public void setDict(List<Propiedad> propiedades) {
        for (int i = 0; i < propiedades.size(); i++){
            Propiedad actual = propiedades.get(i);
            if (this.DiccionarioProps.containsKey(actual.color)){
                List<Propiedad> anterior = this.DiccionarioProps.get(actual.color);
                anterior.add(actual);
            }

        }
    }

    public void setPropietario(Jugador propietario) {
        if (propietario.restarPlata((int)this.getPrecio())){
            this.propietario = propietario;
            this.estado = EstadoPropiedad.COMPRADO;
        }
    }
    public Construcciones getConstrucciones(){
        return this.construcciones;
    }

    private Construcciones getSiguienteConstruccion(Construcciones construccionActual) {
        Construcciones siguiente = construccionActual.siguiente();
        return siguiente;

    }
    private boolean validarPropietario(Jugador propietario){
        return propietario == this.getPropietario();
    }

    public void mejorarPropiedad() {
        Construcciones construccionActual = this.getConstrucciones();
        Construcciones siguienteConstruccion = this.getSiguienteConstruccion(construccionActual);
        this.construcciones= siguienteConstruccion;
    }
    public void vender(Jugador propietario){
        // FALTA DETERMINAR A CUANTO LA VA VENDER. NICO RES= A UN 20% MENOS DE LO QUE LA COMPRO?
        int precio = 0;
        if (validarPropietario(propietario) && this.construcciones == Construcciones.SIN_CASA){
            this.estado = EstadoPropiedad.EN_VENTA;
            this.propietario = null;
            propietario.sumarPlata(precio);
        } else{
            System.out.println("No sos el propietario o tiene casas");
        }
    }

    public void liberarPropiedad(Jugador jugador){
        // Faltaria chequear que primero hayan vendido todas las casas
        this.estado = estado.EN_VENTA;
        this.propietario = null;
    }

}
