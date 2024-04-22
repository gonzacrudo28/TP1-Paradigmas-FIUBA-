package org.example.model;

public abstract class Comprable {

    protected double precio;
    protected int alquiler;
    protected Jugador propietario;
    protected EstadoPropiedad estado;
    protected int ubicacion;

    public enum EstadoPropiedad {
        COMPRADO, EN_VENTA, HIPOTECADO
    }

    public Comprable(double precio, int ubicacion) {
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.propietario = null;
        this.estado = EstadoPropiedad.EN_VENTA;
    }

    public EstadoPropiedad getEstado() {
        return this.estado;
    }
    public int getPrecio() { return (int)precio; }
    public double getAlquiler(){ return alquiler; }
    public Jugador getPropietario(){ return propietario;}
    public int getUbicacion() { return this.ubicacion; }
    public void setUbicacion(int ubicacion){ this.ubicacion = ubicacion; }
    public abstract void setPropietario(Jugador propietario);
    public abstract void liberar();
}
