package org.example.controller;
import org.example.model.Propiedad;

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

    public void ActualizarPropiedad(Propiedad propiedad){
        List<Propiedad> nueva = new ArrayList<Propiedad>();
        List<Propiedad> actual = this.DictPropiedades.get(propiedad.getColor());
        for (int i=0; i<actual.size(); i++) {
            Propiedad actualProp = actual.get(i);
            if (actual.get(i).getNombre() == propiedad.getNombre()){
                actualProp.copy(propiedad);
            }
            nueva.add(actualProp);
        }
        this.DictPropiedades.put(propiedad.getColor(), nueva);
    }

}
