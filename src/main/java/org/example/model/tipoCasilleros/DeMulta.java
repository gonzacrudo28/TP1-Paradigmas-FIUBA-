package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

public class DeMulta{
    public void CobrarMulta(int multa,Jugador jugador){
        int dinero;
        dinero = jugador.getPlata();
        if (dinero < multa){
            System.out.println("Ups!" + jugador.getNombre() + "No tiene dinero suficiente para pagar la deuda");
            //Agregar parte de Controller config
        }else{
         jugador.setPlata(jugador.getPlata()-multa);
        }
    }
}
