package org.example.controller;
import org.example.model.Barrio;
import org.example.model.Jugador;
import org.example.model.Propiedad;
import org.example.model.tipoCasilleros.DePropiedad;

import java.util.ArrayList;
import java.util.List;

public class ConstruccionController {
    private Propiedad propiedad;
    private Barrio barrio;

    public ConstruccionController(Propiedad propiedad, Barrio barrio) {
        this.propiedad = propiedad;
        this.barrio = barrio;
    }

    public boolean validarConstruccion(Jugador jugador){
        //EN LA SEGUNDA COMPRA ESTA DANDO FALSO, DESPUES LO ARREGLO FUNCION PUEDE CONSTRUIR
        System.out.println(this.puedeConstruir(jugador));
        if (this.esPropietarioBarrio(jugador) && this.puedeConstruir(jugador)){
            return true;
        }
        return  false;
    }

    private boolean esPropietarioBarrio(Jugador jugador){
        ArrayList<Propiedad> listaDePropiedades = this.barrio.getPropiedades();
        for (Propiedad propiedad : listaDePropiedades){
            if (propiedad.getPropietario() != jugador){
                System.out.println("ERROR: EL JUGADOR "+jugador.getNombre()+ " NO POSEE TODAS LAS PROPIEDADES DEL BARRIO NUMERO "+ this.barrio.getNumeroBarrio());
                return false;
            }
        }
        return true;
    }

    private boolean puedeConstruir(Jugador jugador){
        ArrayList<Propiedad> listaDePropiedades = this.barrio.getPropiedades();
        int maxima_diferencia = 0;
        for (int i=0; i<listaDePropiedades.size(); i++) {
            for (int j=0; j < listaDePropiedades.size(); j++) {
                int dif = listaDePropiedades.get(i).getConstrucciones().ordinal() - listaDePropiedades.get(j).getConstrucciones().ordinal();
                if (dif > maxima_diferencia) {
                    maxima_diferencia = dif;
                }
            }
        }
        if (maxima_diferencia > 1){
            System.out.println("ERROR: EL JUGADOR "+jugador.getNombre()+ " NO PUEDE CONSTUIR DEBIDO A QUE EXCEDE EL LIMITE DE DIFERENCIA ENTRE LAS CONTRUCCIONES DE LAS PROPIEDADES");
        }
        return maxima_diferencia < 1;
    }
    //Obs -> maxima_diferencia < 1 xq si pongo <= y efectivamente la diferencia es 1,
    // si agrego una casa esa diferencia es= 2(>1)
}
