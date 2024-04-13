package org.example.model;

public class Acciones {


    public enum Accion {
        TERMINAR_TURNO, CONSTRUIR, REFORMAR, VENDER, HIPOTECAR,
        COMPRAR, PAGAR_FIANZA, DESHIPOTECAR;

    }
    public Accion getAccion(int accion){
        if(accion >= 0 && accion < Accion.values().length){
            return Accion.values()[accion];
        }
        return null;
    }

    public void mostrarAcciones(){
        for (int i = 0; i < Accion.values().length; i++){
            System.out.println(i +" -> " +  Accion.values()[i]);
        }
    }
}