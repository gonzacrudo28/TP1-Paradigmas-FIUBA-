package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

import java.util.Random;

public class DeLoteria extends Casillero{

    public void  DarLoteria(Jugador jugador){
        Random random = new Random();
        int randomNumber = random.nextInt(100,1000);
        System.out.println("Ganaste " + randomNumber);
        jugador.setPlata(jugador.getPlata()+randomNumber);
    }

}
