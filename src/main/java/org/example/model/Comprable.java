package org.example.model;

import org.example.controller.Constantes;
import org.example.model.EstadoPropiedades;

import java.util.ArrayList;

public abstract class Comprable {

    protected double precio;
    protected double alquiler;
    protected Jugador propietario;
    protected EstadoPropiedades estado;
    protected int ubicacion;




    public Comprable(double precio, int ubicacion) {
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.propietario = null;
        this.estado = EstadoPropiedades.EN_VENTA;
        this.alquiler = (precio * Constantes.PORCENTAJE_ALQUILER);
    }

    public EstadoPropiedades getEstado() {
        return this.estado;
    }
    public double getPrecio() { return precio; }
    public double getAlquiler(){ return alquiler; }
    public Jugador getPropietario(){ return propietario;}
    public int getUbicacion() { return this.ubicacion; }
    public void setUbicacion(int ubicacion){ this.ubicacion = ubicacion; }
    public abstract void setPropietario(Jugador propietario);
    public void liberar() {
        this.propietario = null;
        this.estado = EstadoPropiedades.EN_VENTA;
    }
    public Comprable getComprable() {
        return this;
    };

}
