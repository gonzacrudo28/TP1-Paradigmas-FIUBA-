package org.example.model.tipoCasilleros;

abstract public class Casillero {
    protected String efecto;
    public TipoCasillero tipo;
    public int ubicacion;

    public void mostrarEfecto(){
        System.out.println(efecto);
    }

    public TipoCasillero getTipo() {return tipo;}
}



