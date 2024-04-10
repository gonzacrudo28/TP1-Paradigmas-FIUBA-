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

    public boolean puedeConstruir(Jugador jugador, Propiedad propiedad){
        List<Propiedad> lista = this.DictPropiedades.get(propiedad.getColor());
        for (Propiedad propiedadAux : lista) {
            if (!propiedadAux.validarPropietario(jugador)) {
                return false;
            }
        }

        int maxima_diferencia = 0;
        for (int i=0; i<lista.size(); i++) {
            for (int j=0; j < lista.size(); j++) {
                int dif = lista.get(i).getConstrucciones().ordinal() - lista.get(j).getConstrucciones().ordinal();
                if (dif > maxima_diferencia) {
                    maxima_diferencia = dif;
                }
            }
        }
        return maxima_diferencia < 1;
        //Obs -> maxima_diferencia < 1 xq si pongo <= y efectivamente la diferencia es 1,
        // si agrego una casa esa diferencia es= 2(>1)
    }

}
