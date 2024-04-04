package org.example.model.tipoCasilleros;

enum TipoPropiedad{
    DeMulta,DeLoteria,DePropiedad,IrALaCarcel,Carcel,Estaciones,LlegadaPartida
}
abstract public class Casillero {
    protected String efecto;
    public TipoPropiedad tipo;
    public int ubicacion;

}



