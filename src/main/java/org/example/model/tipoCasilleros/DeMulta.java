package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

public class DeMulta{
    public void CobrarMulta(int multa,Jugador jugador){
        if (jugador.restarPlata(multa)){
            System.out.println(jugador.getNombre() + "pagaste multa de $"+multa);
        }
    }
}
