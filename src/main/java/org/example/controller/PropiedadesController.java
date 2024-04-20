package org.example.controller;
import org.example.model.Barrio;
import org.example.model.Propiedad;
import org.example.model.Jugador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PropiedadesController {
    private HashMap<Integer, List<Propiedad>> dictPropiedades;
    public PropiedadesController(List<Propiedad> propiedades) {
        dictPropiedades = new HashMap<Integer,List<Propiedad>>();
        for (Propiedad actual : propiedades) {
            if (!dictPropiedades.containsKey(actual.getBarrio())) {
                List<Propiedad> propiedadesAux = new ArrayList<>();
                propiedadesAux.add(actual);
                dictPropiedades.put(actual.getBarrio(), propiedadesAux);
            } else {
                dictPropiedades.get(actual.getBarrio()).add(actual);
            }
        }
    }

    public boolean puedeConstruir(Jugador jugador, Propiedad propiedad){
        List<Propiedad> lista = dictPropiedades.get(propiedad.getBarrio());
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
