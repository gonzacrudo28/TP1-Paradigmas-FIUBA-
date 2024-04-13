package org.example.model.tipoCasilleros;

import org.example.model.Jugador;

import java.util.Random;

public class DeLoteria extends Casillero{
    public DeLoteria() {
        this.efecto = "DeLoteria";
        this.tipo=TipoCasillero.LOTERIA;
    }
    public void  DarLoteria(Jugador jugador){
        Random random = new Random();
        int randomNumber = random.nextInt(100,1000);
        System.out.println("Ganaste! $" + randomNumber + "Felicitaciones!");
        jugador.sumarPlata(randomNumber);
    }

}
