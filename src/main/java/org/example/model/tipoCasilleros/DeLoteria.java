package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

import java.util.Random;

public class DeLoteria extends Casillero{
    public DeLoteria(int ubicacion){
        super("DeLoteria",TipoCasillero.LOTERIA,ubicacion);
    }
    public void  DarLoteria(Jugador jugador){
        Random random = new Random();
        int randomNumber = random.nextInt(100,1000);
        System.out.println("Ganaste! $" + randomNumber + "Felicitaciones!");
        jugador.sumarPlata(randomNumber);
    }

}
