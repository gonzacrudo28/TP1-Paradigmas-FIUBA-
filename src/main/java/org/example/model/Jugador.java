package org.example.model;

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
    public void setCondena(int condena){
        this.condena = condena;
    }
}

