package org.example.view;

import org.example.model.Jugador;
import org.example.model.Tablero;

import java.util.ArrayList;
import java.util.HashMap;

public class TableroView {
    private  Tablero tablero;
    private HashMap<Integer,String> posciones;
    public TableroView(Tablero tablero){
        this.tablero= tablero;
    }

    public void mostrar(ArrayList<Jugador> jugadores){
         posciones= new HashMap<>();
        for(Jugador jugador: jugadores){
            posciones.put(jugador.getUbicacion(),jugador.getNombre());
        }
            for (int i = 0; i< tablero.getCantidadCasilleros(); i++){
                if (posciones.containsKey(i)){
                    System.out.printf("%d %s - %s\n",i, tablero.getCasillero(i).getEfecto(),posciones.get(i));
                }else{
                    System.out.printf("%d %s\n",i, tablero.getCasillero(i).getEfecto());
                }
        }

    }

}
