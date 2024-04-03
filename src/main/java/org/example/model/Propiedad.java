package org.example.model;

import org.example.model.Jugador;

// Enum EstadoPropiedad
enum EstadoPropiedad {
    COMPRADO, VACIO, HIPOTECADO
}

// Enum Construcciones
enum Construcciones {
    SIN_CASA, UNA_CASA, DOS_CASAS, TRES_CASAS, CUATRO_CASAS, HOTEL
}

// Clase Propiedad
public class Propiedad {
    private String nombre;
    private double precio;
    private String color;
    private double alquiler;
    private Jugador propietario;
    private EstadoPropiedad estado;
    private Construcciones construcciones;

    // Constructor
    public Propiedad(String nombre, double precio, String color, double alquiler) {
        this.nombre = nombre;
        this.precio = precio;
        this.color = color;
        this.alquiler = alquiler;
        this.propietario = null;
        this.estado = EstadoPropiedad.VACIO;
        this.construcciones = Construcciones.SIN_CASA;
    }
    public EstadoPropiedad getEstado() {
        return estado;
    }
    public double getPrecio() {
        return precio;
    }
    public double getAlquiler() {
        return alquiler;
    }
    public Jugador getPropietario() {
        return propietario;
    }
    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
        this.estado = EstadoPropiedad.COMPRADO;
    }
    public Construcciones getConstrucciones(){
        return this.construcciones;
    }
    private Construcciones getSiguienteConstruccion(Construcciones construccionActual) {
        switch (construccionActual) {
            case SIN_CASA:
                return Construcciones.UNA_CASA;
            case UNA_CASA:
                return Construcciones.DOS_CASAS;
            case DOS_CASAS:
                return Construcciones.TRES_CASAS;
            case TRES_CASAS:
                return Construcciones.CUATRO_CASAS;
            case CUATRO_CASAS:
                return Construcciones.HOTEL;
            default:
                return construccionActual;
        }
    }
    private boolean validarPropietario(Jugador propietario){
        return propietario == this.getPropietario();
    }
    public void mejorarPropiedad(Jugador constructor) {
        if (validarPropietario(constructor)) {
            Construcciones construccionActual = this.getConstrucciones();
            Construcciones siguienteConstruccion = this.getSiguienteConstruccion(construccionActual);
            this.construcciones= siguienteConstruccion;
        }
    }
    public void vender(Jugador propietario){
        if (validarPropietario(propietario)){
            this.estado = EstadoPropiedad.VACIO;
            this.propietario = null;
        }
    }
}
