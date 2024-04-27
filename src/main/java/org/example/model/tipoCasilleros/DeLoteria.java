package org.example.model.tipoCasilleros;

import org.example.model.Jugador;
import java.util.Random;

public class DeLoteria extends Casillero implements CasilleroEjecutable{
    private final double dineroIncial ;

    public DeLoteria(int ubicacion,double dineroIncial){
        super("DeLoteria",TipoCasillero.LOTERIA,ubicacion);
        this.dineroIncial = dineroIncial;
    }

    public void  ejecutarCasillero(Jugador jugador){
        Random random = new Random();
        int dineroInicialInt = (int) dineroIncial;
        int randomNumber = random.nextInt(0,dineroInicialInt);
        System.out.println("Felicitaciones, ganaste! $" + randomNumber + " pesos");
        jugador.sumarPlata(randomNumber);
    }
}
