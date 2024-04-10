package org.example.controller;
import org.example.model.Propiedad;
import org.example.model.Jugador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PropiedadesController {
    private HashMap<String, List<Propiedad>> DictPropiedades;
    public PropiedadesController(List<Propiedad> propiedades) {
        this.DictPropiedades = new HashMap<>();
        for (Propiedad actual : propiedades) {
            if (!this.DictPropiedades.containsKey(actual.getColor())) {
                List<Propiedad> propiedadesAux = new ArrayList<>();
                propiedadesAux.add(actual);
                this.DictPropiedades.put(actual.getColor(), propiedadesAux);
            } else {
                this.DictPropiedades.get(actual.getColor()).add(actual);
            }
        }
    }

    public boolean puedeConsirtruir(Jugador jugador, Propiedad propiedad){
        List<Propiedad> lista = this.DictPropiedades.get(propiedad.getColor());
        for (Propiedad propiedadAux : lista) {
            if (propiedadAux.getPropietario() != jugador) {
                return false;
            }
        }
        int maxima_diferencia = 0;
        for (int i=0; i<lista.size(); i++) {
            
        }
        return maxima_diferencia <= 1;
    }

}
