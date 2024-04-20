package org.example.model;

import org.example.model.Propiedad;
import org.example.model.tipoCasilleros.Casillero;

import java.util.ArrayList;
import java.util.List;

public class Barrio {
    private List<Casillero> casillerosPropiedades;
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

    public List<Casillero> getCasillerosPropiedades() {
        return casillerosPropiedades;
    }

    public void addCasillero(Casillero casillero) {
        this.casillerosPropiedades.add(casillero);
    }

    public double getPrecioBarrio() {
        return precioBarrio;
    }
}
