package org.example.view;

import org.example.model.Colores;
import org.example.model.Jugador;
import org.example.model.Tablero;
import org.example.model.tipoCasilleros.Casillero;
import org.fusesource.jansi.Ansi;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class TableroView {
    private  Tablero tablero;
    private HashMap<Integer,String> posciones;
    private HashMap<Integer, ArrayList<Jugador>> posJugador;
    public TableroView(Tablero tablero){
        this.tablero= tablero;
    }

    //OPTIMIZAR ESTA FUNCION
    public void mostrar(ArrayList<Jugador> jugadores){
        System.out.println("NUM -   CASILLERO   -   JUGADOR/ES");
        this.posJugador= new HashMap<>();
        for(Jugador jugador: jugadores){
            if (posJugador.get(jugador.getUbicacion())!=null){
                posJugador.get(jugador.getUbicacion()).add(jugador);
            }else{
                ArrayList<Jugador> listaDeJugador = new ArrayList<>();
                listaDeJugador.add(jugador);
                posJugador.put(jugador.getUbicacion(),listaDeJugador);
            }
        }
        for (int i = 0; i< tablero.getCantidadCasilleros(); i++){
            ArrayList<Jugador> jugadoresEnPosicion = posJugador.get(i);
            if (jugadoresEnPosicion!=null){
                if (posJugador.containsKey(i)){
                    //PRODRIA SER UN ENUM EN VEZ DE PREGUNTAR POR EL EFECTO
                    //ARREGLAR, ROMPE DRY
                    if (tablero.getCasillero(i).getEfecto()!= "Propiedad" && tablero.getCasillero(i).getEfecto()!= "De transporte"){
                        System.out.print(i +" "+ tablero.getCasillero(i).getEfecto());
                    }else {
                        System.out.print(i + " " + tablero.getCasillero(i).getEfecto() + " (VALOR: $" + tablero.getCasillero(i).getPrecio()+ ")");
                    }
                }
                imprimirNombresJugadores(jugadoresEnPosicion);
            }else{
                if (tablero.getCasillero(i).getEfecto()!= "Propiedad" && tablero.getCasillero(i).getEfecto()!= "De transporte"){
                    System.out.println(i +" "+ tablero.getCasillero(i).getEfecto());
                }else {
                    System.out.println(i + " " + tablero.getCasillero(i).getEfecto() + " (VALOR: $" + tablero.getCasillero(i).getPrecio()+ ")");
                }
            }
        }
    }
    private void imprimirNombresJugadores(ArrayList<Jugador> jugadores){
        Ansi colorANSI = null;
        Ansi resetColor = null;
        for (Jugador jugador: jugadores){
            colorANSI = obtenerColorANSI(jugador.getColor());
            resetColor = Ansi.ansi().reset();
            System.out.print(" " + colorANSI + jugador.getNombre() + resetColor + " ");
        }
        System.out.println();
    }
    /*
    public void mostrarR(ArrayList<Jugador> jugadores){
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

    }*/

    //ESTA FUNCION TAMBIEN ESTA EN JUGADORVIEW, HAY Q USAR UNA PARA LOS 2
    private Ansi obtenerColorANSI(Colores.Color color) {
        try {
            Field field = Ansi.Color.class.getDeclaredField(color.name());
            Ansi.Color ansiColor = (Ansi.Color) field.get(null);
            return Ansi.ansi().fg(ansiColor);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return Ansi.ansi();
        }
    }
}
