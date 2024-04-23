package org.example.model;
import org.fusesource.jansi.Ansi;

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

    public void getAcciones(Ansi colorANSI,Ansi resetColor){
        for (int i = 0; i < Accion.values().length; i++){
            System.out.println( colorANSI+""+ i +" -> " +  Accion.values()[i] + resetColor);
        }
    }

    public void getAccionesJugadorPreso(){
        int indice = 1;
        for (int i = 0; i < Accion.values().length; i++){
            if (Accion.values()[i].equals(Accion.PAGAR_FIANZA) || Accion.values()[i].equals(Accion.TERMINAR_TURNO)){
                System.out.println( indice+ " -> " +  Accion.values()[i]);
                indice ++;
            }
        }
    }

    public Accion getAccionPreso(int accion) {
        if (accion == 1){
            //PAGAR FIANZA
            return this.getAccion(6);
        }
        return this.getAccion(0);
    }

}