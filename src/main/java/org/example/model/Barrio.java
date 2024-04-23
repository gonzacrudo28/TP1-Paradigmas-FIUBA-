package org.example.model;

import org.example.model.Propiedad;
import org.example.model.tipoCasilleros.Casillero;
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
    }

    public int getNumeroBarrio() {
        return numeroBarrio;
    }


    public void setPropietarioBarrio(Jugador jugador){
        if (esPropietarioBarrio(jugador)){
            this.propietarioBarrio = jugador;
        }
    }
//esta misma funcion esta en CONSTRUCCION CONTROLER
    private boolean esPropietarioBarrio(Jugador jugador){
        ArrayList<Propiedad> listaDePropiedades = this.getPropiedades();
        for (Propiedad propiedad : listaDePropiedades){
            if (propiedad.getPropietario() != jugador){
                System.out.println("ERROR: EL JUGADOR "+jugador.getNombre()+ " NO POSEE TODAS LAS PROPIEDADES DEL BARRIO NUMERO "+ this.getNumeroBarrio());
                return false;
            }
        }
        return true;
    }

    public ArrayList<Propiedad> getPropiedades(){
        ArrayList<Propiedad> propiedades = new ArrayList<>();
        for (DePropiedad casillero : this.casillerosPropiedades){
            propiedades.add(casillero.getPropiedad());
        }
        return propiedades;
    }







    public void addCasillero(DePropiedad casillero) {
        this.casillerosPropiedades.add(casillero);
    }

    public double getPrecioBarrio() {
        return precioBarrio;
    }
}
