package org.example.model;
import org.example.model.Jugador;
import org.example.model.Propiedad;
public class Banco {

    public void pagarBono(Jugador jugador, int bono){
        jugador.sumarPlata(bono);
    }
}
