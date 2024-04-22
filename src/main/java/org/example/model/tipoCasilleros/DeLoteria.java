package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

import java.util.Random;

public class DeLoteria extends Casillero implements CasilleroEjecutable{
    public DeLoteria(int ubicacion){
        super("DeLoteria",TipoCasillero.LOTERIA,ubicacion);
    }

    public void  ejecutarCasillero(Jugador jugador){
        Random random = new Random();
        int randomNumber = random.nextInt(100,1000);
        System.out.println("Felicitaciones, ganaste! $" + randomNumber + " pesos");
        jugador.sumarPlata(randomNumber);
    }
}
