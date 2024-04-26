package org.example.model;


import org.example.model.tipoCasilleros.DePropiedad;
import java.util.ArrayList;
import java.util.List;

public class Barrio {
    private List<DePropiedad> casillerosPropiedades;
    private Jugador propietarioBarrio;
    private double precioBarrio;
    private int numeroBarrio;

    public Barrio(int numeroBarrio,double precioBarrio) {
        this.numeroBarrio = numeroBarrio;
        this.casillerosPropiedades = new ArrayList<>();
        this.precioBarrio = precioBarrio;
        this.propietarioBarrio = null;
    }

    public int getNumeroBarrio() {
        return numeroBarrio;
    }

    public void setPropietarioBarrio(Jugador jugador){

        this.propietarioBarrio = jugador;

    }

    public ArrayList<Propiedad> getPropiedades(){
        ArrayList<Propiedad> propiedades = new ArrayList<>();
        for (DePropiedad casillero : this.casillerosPropiedades){
            propiedades.add(casillero.getPropiedad());
        }
        return propiedades;
    }

    public Jugador getPropietario(){
        return propietarioBarrio;
}

    public void addCasillero(DePropiedad casillero) {
        this.casillerosPropiedades.add(casillero);
    }
}
