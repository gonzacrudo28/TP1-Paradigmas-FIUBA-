package org.example.controller;
import org.example.model.Barrio;
import org.example.model.Construcciones;
import org.example.model.Jugador;
import org.example.model.Propiedad;
import org.example.model.tipoCasilleros.DePropiedad;
import org.example.controller.Constantes;

import java.util.ArrayList;
import java.util.List;

//.

public class ConstruccionController {
    private ArrayList<Barrio> barrios;


    public ConstruccionController(ArrayList<Barrio> barrios) {
        this.barrios = barrios;
    }

    public void vender(Jugador jugador,Propiedad propiedad){
        Barrio barrio = barrios.get(propiedad.getBarrio());
        if(barrio.getPropietario()!=jugador){
            System.out.println("No sos el dueño de este barrio");
            return;
        }
        if(propiedad.getConstrucciones() == Construcciones.SIN_CASA){
            if (validarVentaTerreno(barrio)){
                double precioReventa = propiedad.getPrecio()*Constantes.PORCENTAJE_DE_VENTA;
                jugador.restarPatrimonio(precioReventa);
                propiedad.venderComprable();
            }
            //Caso donde se quiera vender el terreno
        }else{
            //Caso donde se quiere vender una casa
            if (validarVenta(jugador,propiedad)){
                double precioReventa = propiedad.getPrecioCasa() * Constantes.PORCENTAJE_DE_VENTA;
                jugador.sumarPlata(precioReventa);
                jugador.restarPatrimonio(precioReventa);
                deconstruirCasa(barrio,jugador,propiedad);
            }
        }
    }


    public boolean validarVentaTerreno(Barrio barrio){
        List<Propiedad> propiedadList = barrio.getPropiedades();
        for (Propiedad prop: propiedadList) {
            if (prop.getConstrucciones() != Construcciones.SIN_CASA) {
                System.out.println("No puedes vender el terreno porque hay otras propiedades con casas");
                return false;
            }
        }
        return true;
    }

    public boolean validarVenta(Jugador jugador,Propiedad propiedad){
        if (barrios.get(propiedad.getBarrio()).getPropietario() == jugador){
            return false;
        }
        ArrayList<Propiedad> listaDePropiedades = this.barrios.get(propiedad.getBarrio()).getPropiedades();
        for (Propiedad propiedadLista : listaDePropiedades) {
            int dif = propiedad.getConstrucciones().ordinal() - propiedadLista.getConstrucciones().ordinal();
            if (dif > 0) {
                System.out.println("ERROR: EL JUGADOR " + jugador.getNombre() + " NO PUEDE VENDER DEBIDO A QUE EXCEDE EL LIMITE DE DIFERENCIA ENTRE LAS CONTRUCCIONES DE LAS PROPIEDADES");
                return false;
            }
        }
        return true;
    }


    public boolean validarConstruccion(Jugador jugador,int barrio,Propiedad propiedad){
        if (!esPropietarioBarrio(jugador,barrios.get(barrio))) {
            return false;
        }
        if (jugador.getPlata() < propiedad.getPrecioCasa()){
            System.out.println("ERROR: EL JUGADOR " +jugador.getNombre()+ " NO TIENE SUFICIENTE PLATA");
            return false;
        }
        if (propiedad.getConstrucciones() == Construcciones.HOTEL){
            System.out.println("ERROR: EL JUGADOR "+jugador.getNombre()+ "YA POSEE UN HOTEL EN ESTA PROPIEDAD");
            return false;
        }
        ArrayList<Propiedad> listaDePropiedades = barrios.get(propiedad.getBarrio()).getPropiedades();
        for (int i=0; i<listaDePropiedades.size(); i++) {
            int dif = (propiedad.getConstrucciones().ordinal()) - listaDePropiedades.get(i).getConstrucciones().ordinal();
            if (dif>0){
                System.out.println("ERROR: EL JUGADOR "+jugador.getNombre()+ " NO PUEDE CONSTUIR DEBIDO A QUE EXCEDE EL LIMITE DE DIFERENCIA ENTRE LAS CONTRUCCIONES DE LAS PROPIEDADES");
                return  false;
            }
        }
        return true;
    }

    public void construirEnPropiedad(Jugador jugador,Propiedad propiedad){
        if (validarConstruccion(jugador, propiedad.getBarrio(),propiedad)){
            propiedad.sumarConstruccion();
            System.out.println("ALQUILER VIEJO " +propiedad.getAlquiler());
            propiedad.actualizarAlquiler();
            System.out.println("ALQUILER NUEVO "+propiedad.getAlquiler());
            jugador.restarPlata(propiedad.getPrecioCasa());
            jugador.sumarAlPatrimonio(propiedad.getPrecioCasa());
            System.out.println("Propiedad mejorada a "+propiedad.getConstrucciones()+" con exito");
        }
    }

    public boolean esPropietarioBarrio(Jugador jugador,Barrio barrio){
        ArrayList<Propiedad> listaDePropiedades = barrio.getPropiedades();
        for (Propiedad propiedad : listaDePropiedades){
            if (propiedad.getPropietario() != jugador){
                System.out.println("ERROR: EL JUGADOR "+jugador.getNombre()+ " NO POSEE TODAS LAS PROPIEDADES DEL BARRIO NUMERO "+ barrio.getNumeroBarrio());
                return false;
            }
        }
        barrio.setPropietarioBarrio(jugador);
        return true;
    }



    //Obs -> maxima_diferencia < 1 xq si pongo <= y efectivamente la diferencia es 1,
    // si agrego una casa esa diferencia es= 2(>1)
    public void deconstruirCasa(Barrio barrio, Jugador jugador,Propiedad propiedad){
            jugador.restarPatrimonio(propiedad.getPrecioCasa());
            propiedad.restarConstruccion();
            propiedad.actualizarAlquiler();
    }
}
