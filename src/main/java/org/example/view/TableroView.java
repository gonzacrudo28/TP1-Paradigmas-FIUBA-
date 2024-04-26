package org.example.view;

import org.example.funciones.FuncionColorPrints;
import org.example.model.EstacionTransporte;
import org.example.model.Jugador;
import org.example.model.Barrio;
import org.example.model.Propiedad;
import org.example.model.Tablero;
import org.example.model.tipoCasilleros.DePropiedad;
import org.example.model.tipoCasilleros.Estacion;
import org.fusesource.jansi.Ansi;
import java.util.ArrayList;
import java.util.HashMap;

public class TableroView {
    private  Tablero tablero;
    private HashMap<Integer,String> posciones;
    private HashMap<Integer, ArrayList<Jugador>> posJugador;

    public TableroView(Tablero tablero){
        this.tablero= tablero;
    }

    public void mostrar2(ArrayList<Jugador> jugadores){
        int anchoNumero = String.valueOf(tablero.getCantidadCasilleros()).length();
        int anchoCasillero = 20;
        int anchoPrecio = 10;
        int anchoBarrio = 10;
        int anchoPropietario = 15;
        Ansi colorANSI = Ansi.ansi().reset();
        Ansi resetColor = Ansi.ansi().reset();
        String formato = "|"+"%-" + anchoNumero + "s" + "|" + "%-" + anchoCasillero + "s" + "|" + "%-" + anchoPrecio + "s" + "|" + "%-" + anchoBarrio + "s" + "|" + "%-" + anchoPropietario + "s" + "|";
        System.out.println(String.format(formato, "N°", "CASILLERO", "PRECIO", "BARRIO", "PROPIETARIO", "JUGADORES") + " JUGADORES");
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
        FuncionColorPrints funcionColorPrints = new FuncionColorPrints();
        for (int i = 0; i< tablero.getCantidadCasilleros(); i++){
            ArrayList<Jugador> jugadoresEnPosicion = posJugador.get(i);
            if (jugadoresEnPosicion!=null){
                if (posJugador.containsKey(i)){
                    if (tablero.getCasillero(i).getEfecto()!= "Propiedad" && tablero.getCasillero(i).getEfecto()!= "De transporte"){
                        System.out.print(String.format(formato, i, tablero.getCasillero(i).getEfecto(), "", "", ""));
                    }else {
                        if (tablero.getCasillero(i) instanceof DePropiedad){
                            ArrayList<Barrio> barrios = tablero.getBarrios();
                            for (Barrio barrio: barrios){
                                for (Propiedad propiedad: barrio.getPropiedades()){
                                    Jugador propietario = propiedad.getPropietario();
                                    if (propiedad == tablero.getCasillero(i).getPropiedad()){
                                        if (propietario == null){
                                            if (propiedad == tablero.getCasillero(i).getPropiedad()){
                                                System.out.print(String.format(formato, i, tablero.getCasillero(i).getEfecto(), "$" + tablero.getCasillero(i).getPrecio(), "BARRIO:" +  barrio.getNumeroBarrio(), "SIN PROPIETARIO"));
                                            }
                                        }else{
                                            String nombre = propiedad.getNombrePropietario();
                                            colorANSI = funcionColorPrints.obtenerColorANSI(propietario.getColor());
                                            resetColor = Ansi.ansi().reset();
                                            System.out.print(colorANSI+String.format(formato, i, tablero.getCasillero(i).getEfecto(), "$" + tablero.getCasillero(i).getPrecio(), "BARRIO:" +  barrio.getNumeroBarrio(), nombre)+resetColor);
                                        }
                                    }
                                }
                            }
                        }else{
                            EstacionTransporte propiedad= tablero.getCasillero(i).getPropiedad();
                            Jugador propietario = propiedad.getPropietario();
                            if (propietario != null){
                                colorANSI = funcionColorPrints.obtenerColorANSI(propietario.getColor());
                                resetColor = Ansi.ansi().reset();
                                String nombre = propiedad.getNombrePropietario();
                                System.out.print(colorANSI+String.format(formato, i, tablero.getCasillero(i).getEfecto(), "$"+tablero.getCasillero(i).getPrecio(), "", nombre)+resetColor);
                            }else{
                                System.out.print(String.format(formato, i, tablero.getCasillero(i).getEfecto(), "$"+tablero.getCasillero(i).getPrecio(), "", "SIN PROPIETARIO"));
                            }
                        }
                    }
                }
                imprimirNombresJugadores(jugadoresEnPosicion);
            }else{
                if (tablero.getCasillero(i) instanceof DePropiedad || tablero.getCasillero(i) instanceof Estacion){
                    if (tablero.getCasillero(i) instanceof DePropiedad){
                        ArrayList<Barrio> barrios = tablero.getBarrios();
                        for (Barrio barrio: barrios){
                            for (Propiedad propiedad: barrio.getPropiedades()){
                                Jugador propietario = propiedad.getPropietario();
                                if (propiedad == tablero.getCasillero(i).getPropiedad()){

                                    if (propietario ==null){
                                        System.out.println(String.format(formato, i, tablero.getCasillero(i).getEfecto(), "$"+ tablero.getCasillero(i).getPrecio(), "BARRIO:"+ barrio.getNumeroBarrio(), "SIN PROPIETARIO"));
                                    }else{
                                        colorANSI = funcionColorPrints.obtenerColorANSI(propietario.getColor());
                                        resetColor = Ansi.ansi().reset();
                                        String nombre = propietario.getNombre();
                                        System.out.println(colorANSI+ String.format(formato, i, tablero.getCasillero(i).getEfecto(), "$"+ tablero.getCasillero(i).getPrecio(), "BARRIO:"+ barrio.getNumeroBarrio(),nombre)+resetColor);
                                    }
                                }
                            }
                        }
                    }else{
                        EstacionTransporte propiedad= tablero.getCasillero(i).getPropiedad();
                        Jugador propietario = propiedad.getPropietario();
                        if (propietario != null){
                            colorANSI = funcionColorPrints.obtenerColorANSI(propietario.getColor());
                            resetColor = Ansi.ansi().reset();
                            String nombre = propietario.getNombre();
                            System.out.println(colorANSI + String.format(formato, i, tablero.getCasillero(i).getEfecto(), "$"+ tablero.getCasillero(i).getPrecio(), "", nombre)+resetColor);
                        }else{
                            System.out.println(String.format(formato, i, tablero.getCasillero(i).getEfecto(), "$"+ tablero.getCasillero(i).getPrecio(), "", "SIN PROPIETARIO"));
                        }
                    }
                }else {
                    System.out.println(String.format(formato, i, tablero.getCasillero(i).getEfecto(), "", "", ""));
                }
            }
        }
    }

    private void imprimirNombresJugadores(ArrayList<Jugador> jugadores){
        Ansi colorANSI = null;
        Ansi resetColor = null;
        FuncionColorPrints funcionColorPrints = new FuncionColorPrints();
        for (Jugador jugador: jugadores){
            colorANSI = funcionColorPrints.obtenerColorANSI(jugador.getColor());
            resetColor = Ansi.ansi().reset();
            System.out.print(" " + colorANSI + jugador.getNombre() + resetColor + " ");
        }
        System.out.println();
    }

    public void mostrar(ArrayList<Jugador> jugadores){
        int anchoNumero = String.valueOf(tablero.getCantidadCasilleros()).length();
        int anchoCasillero = 20;
        int anchoPrecio = 10;
        int anchoBarrio = 10;
        int anchoPropietario = 15;
        Ansi colorANSI = Ansi.ansi().reset();
        Ansi resetColor = Ansi.ansi().reset();
        String formato = "|"+"%-" + anchoNumero + "s" + "|" + "%-" + anchoCasillero + "s" + "|" + "%-" + anchoPrecio + "s" + "|" + "%-" + anchoBarrio + "s" + "|" + "%-" + anchoPropietario + "s" + "|";
        System.out.println(String.format(formato, "N°", "CASILLERO", "PRECIO", "BARRIO", "PROPIETARIO", "JUGADORES") + " JUGADORES");
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
        FuncionColorPrints funcionColorPrints = new FuncionColorPrints();
        for (int i = 0; i< tablero.getCantidadCasilleros(); i++){
            ArrayList<Jugador> jugadoresEnPosicion = posJugador.get(i);
                if (!(tablero.getCasillero(i) instanceof DePropiedad) && !(tablero.getCasillero(i) instanceof Estacion)) {
                    System.out.print(String.format(formato, i, tablero.getCasillero(i).getEfecto(), "", "", ""));
                }else {
                    if (tablero.getCasillero(i) instanceof DePropiedad){
                        ArrayList<Barrio> barrios = tablero.getBarrios();
                        for (Barrio barrio: barrios){
                            for (Propiedad propiedad: barrio.getPropiedades()){
                                Jugador propietario = propiedad.getPropietario();
                                if (propiedad == tablero.getCasillero(i).getPropiedad()){
                                    if (propietario == null){
                                        if (propiedad == tablero.getCasillero(i).getPropiedad()){
                                            System.out.print(String.format(formato, i, tablero.getCasillero(i).getEfecto(), "$" + tablero.getCasillero(i).getPrecio(), "BARRIO:" +  barrio.getNumeroBarrio(), "SIN PROPIETARIO"));
                                        }
                                    }else{
                                        String nombre = propiedad.getNombrePropietario();
                                        colorANSI = funcionColorPrints.obtenerColorANSI(propietario.getColor());
                                        resetColor = Ansi.ansi().reset();
                                        System.out.print(colorANSI+String.format(formato, i, tablero.getCasillero(i).getEfecto(), "$" + tablero.getCasillero(i).getPrecio(), "BARRIO:" +  barrio.getNumeroBarrio(), nombre)+resetColor);
                                    }
                                }
                            }
                        }
                    }else{
                        EstacionTransporte propiedad= tablero.getCasillero(i).getPropiedad();
                        Jugador propietario = propiedad.getPropietario();
                        if (propietario != null){
                            colorANSI = funcionColorPrints.obtenerColorANSI(propietario.getColor());
                            resetColor = Ansi.ansi().reset();
                            String nombre = propiedad.getNombrePropietario();
                            System.out.print(colorANSI+String.format(formato, i, tablero.getCasillero(i).getEfecto(), "$"+tablero.getCasillero(i).getPrecio(), "", nombre)+resetColor);
                        }else{
                            System.out.print(String.format(formato, i, tablero.getCasillero(i).getEfecto(), "$"+tablero.getCasillero(i).getPrecio(), "", "SIN PROPIETARIO"));
                        }
                    }
                }
                if (jugadoresEnPosicion!=null){
                    imprimirNombresJugadores(jugadoresEnPosicion);
                }
                System.out.println("");
        }
    }
}


