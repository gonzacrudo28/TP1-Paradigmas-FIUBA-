package org.example.controller;
import org.example.model.Barrio;
import org.example.model.Jugador;
import org.example.model.Propiedad;
import org.example.model.tipoCasilleros.DePropiedad;

import java.util.ArrayList;
import java.util.List;

//.
public class ConstruccionController {
    private Propiedad propiedad;
    private Barrio barrio;

    public ConstruccionController(Propiedad propiedad, Barrio barrio) {
        this.propiedad = propiedad;
        this.barrio = barrio;
    }

    public boolean validarConstruccion(Jugador jugador,Propiedad propiedad){
        if (this.esPropietarioBarrio(jugador) && this.puedeConstruir(jugador, propiedad)){
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

    private boolean puedeConstruir(Jugador jugador,Propiedad propiedad){
        if (propiedad.getConstrucciones() == Propiedad.Construcciones.HOTEL){
            System.out.println("ERROR: EL JUGADOR "+jugador.getNombre()+ "YA POSEE UN HOTEL EN ESTA PROPIEDAD");
            return false;
        }
        ArrayList<Propiedad> listaDePropiedades = this.barrio.getPropiedades();
        for (int i=0; i<listaDePropiedades.size(); i++) {

            int dif = propiedad.getConstrucciones().ordinal() - listaDePropiedades.get(i).getConstrucciones().ordinal();
            if (dif>0){
                System.out.println("ERROR: EL JUGADOR "+jugador.getNombre()+ " NO PUEDE CONSTUIR DEBIDO A QUE EXCEDE EL LIMITE DE DIFERENCIA ENTRE LAS CONTRUCCIONES DE LAS PROPIEDADES");
                return  false;
            }
            }
        return true;
    }
    //Obs -> maxima_diferencia < 1 xq si pongo <= y efectivamente la diferencia es 1,
    // si agrego una casa esa diferencia es= 2(>1)
}
