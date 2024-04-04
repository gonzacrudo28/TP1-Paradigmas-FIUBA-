package org.example.model;
import org.example.model.Tablero;
import org.example.model.Colores;
import java.util.List;
public class Jugador
{
    private final String nombre;
    private final Colores.Color color;
    private int plata;
    private int ubicacion;
    private List<Propiedad> propiedades;
    private enum estado{EnJuego,Preso,Deuda};

    private int condena;
    public Jugador(String nombre, Colores.Color color, int plata, int ubicacion, List<Propiedad> propiedades) {
        this.nombre = nombre;
        this.color = color;
        this.plata = plata;
        this.ubicacion = ubicacion;
        this.propiedades = propiedades;
    }
    public String getNombre() {
        return this.nombre;
    }

    public int getPlata() {
        return plata;
    }

    public int getUbicacion() {
        return ubicacion;
    }

    public int getCondena(){
        return this.condena;
    }

    public Colores.Color getColor() {
        return color;
    }

    public void setPlata(int plata) {
        this.plata = plata;
    }
    public void setCondena(int condena){this.condena += condena;}
    public boolean restarPlata(int dinero){
        if (this.plata > dinero){
            this.plata -= dinero;
            return True;
        }
        System.out.println("Ups!" + jugador.getNombre() + "no tiene dinero suficiente para pagar esta deuda");
        //Agregar parte de Controller config
        return False;
    }
    public void sumarPlata(int dinero){
        this.plata += dinero;
    }

    public void setUbicacion(int ubicacion) {
        // Falta pasarle la cantidad de casilleros :D
        /
        if (this.condena > 0) {
            this.condena -= 1;
        }
        else{
            int CANT_CASILLEROS = 1000;
            if( this.ubicacion + ubicacion <= CANT_CASILLEROS){
                this.ubicacion += ubicacion;
            } else{
                this.ubicacion = (this.ubicacion + ubicacion - CANT_CASILLEROS);
                this.sumarPlata();
            }
        }
    }

}

