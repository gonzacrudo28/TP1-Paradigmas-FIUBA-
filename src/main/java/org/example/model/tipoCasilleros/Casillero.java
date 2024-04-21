package org.example.model.tipoCasilleros;

abstract public class Casillero {
    protected String efecto;
    protected TipoCasillero tipo;
    protected int ubicacion;

    public Casillero(String efecto, TipoCasillero tipoCasillero, int ubicacion) {
        this.efecto = efecto;
        this.tipo = tipoCasillero;
        this.ubicacion = ubicacion;
    }

    public TipoCasillero getTipo() {return tipo;}

    public String getEfecto(){return efecto;}

    public int getUbicacion(){return ubicacion;}
    public  int getPrecio(){
        return  0;
    };
}





