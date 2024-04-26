package org.example.model.tipoCasilleros;
import org.example.model.Comprable;
import org.example.model.Propiedad;

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
    public double getPrecio(){
        return 0;
    }
    public  <T extends Comprable> T getPropiedad(){
        return null;
    }
}





