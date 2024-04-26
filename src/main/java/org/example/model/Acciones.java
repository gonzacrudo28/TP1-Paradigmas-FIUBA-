package org.example.model;
import org.fusesource.jansi.Ansi;
import org.example.model.Accion;

public class Acciones {
    public Accion getAccion(int accion){
        if(accion >= 0 && accion < Accion.values().length){
            return Accion.values()[accion];
        }
        return null;
    }

    public void getAcciones(Ansi colorANSI,Ansi resetColor){
        for (int i = 0; i < Accion.values().length-1; i++){
            System.out.println( colorANSI+""+ i +" -> " +  Accion.values()[i] + resetColor);
        }
    }

    public void getAccionesJugadorPreso(){
        int indice = 0;
        for (int i = 0; i < Accion.values().length; i++){
            if (Accion.values()[i].equals(Accion.PAGAR_FIANZA) || Accion.values()[i].equals(Accion.TERMINAR_TURNO)){
                System.out.println( indice+ " -> " +  Accion.values()[i]);
                indice ++;
            }
        }
    }

    public Accion getAccionPreso(int accion) {
        if (accion == 1){
            return this.getAccion(6);
        }
        return this.getAccion(0);
    }
}