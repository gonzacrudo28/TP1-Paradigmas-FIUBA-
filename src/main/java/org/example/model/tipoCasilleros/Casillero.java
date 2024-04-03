package org.example.model.tipoCasilleros;

enum TipoPropiedad{
    DeMulta,DeLoteria,DePropiedad,IrALaCarcel,Carcel,Estaciones,LegadaPartida
}
abstract public class Casillero {
    protected String efecto;
    public TipoPropiedad tipo;
    public int ubicacion;

}



