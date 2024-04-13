package org.example.model;

import org.example.model.Propiedad;
import org.example.model.tipoCasilleros.Casillero;

import java.util.ArrayList;
import java.util.List;

public class Barrio {
    private String color;
    private List<Casillero> casillerosPropiedades;

    public Barrio(String color) {
        this.color = color;
        this.casillerosPropiedades = new ArrayList<>();
    }

    public String getColor() {
        return color;
    }

    public List<Casillero> getCasillerosPropiedades() {
        return casillerosPropiedades;
    }

    public void addCasillero(Casillero casillero) {
        this.casillerosPropiedades.add(casillero);
    }
}
