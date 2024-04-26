package org.example.controller;
import org.example.model.Barrio;
import org.example.model.Construcciones;
import org.example.model.Jugador;
import org.example.model.Propiedad;
import java.util.ArrayList;
import java.util.List;


public class ConstruccionController {
    private ArrayList<Barrio> barrios;

    public ConstruccionController(ArrayList<Barrio> barrios) {
        this.barrios = barrios;
    }

    public void venderSiendoPropietarioBarrio(Jugador jugador,Propiedad propiedad, Barrio barrio) {
        System.out.println("Validacion dueño barrio "+propiedad.getConstrucciones());
        if(propiedad.getConstrucciones() == Construcciones.SIN_CASA){
            if (validarVentaTerreno(barrio)){
                double precioReventa = propiedad.getPrecio()*Constantes.PORCENTAJE_DE_VENTA;
                jugador.restarPatrimonio(precioReventa);
                propiedad.venderComprable();
                propiedad.liberar();
                System.out.println("Propiedad vendida con exito!");
            }
        }else{
            System.out.println("Validacion dueño barrio, ELSE "+propiedad.getConstrucciones());
            if (validarVenta(jugador,propiedad)){
                double precioReventa = propiedad.getPrecioCasa();
                jugador.sumarPlata(precioReventa);
                deconstruirCasa(barrio,jugador,propiedad);
            }
        }
    }
    public void vender(Jugador jugador,Propiedad propiedad){
        Barrio barrio = barrios.get(propiedad.getBarrio());
        if (barrio.getPropietario() == jugador){
            venderSiendoPropietarioBarrio(jugador,propiedad,barrio);
        }else{
            double precioReventa = propiedad.getPrecio()*Constantes.PORCENTAJE_DE_VENTA;
            jugador.restarPatrimonio(precioReventa);
            propiedad.venderComprable();
            propiedad.liberar();
            System.out.println("Propiedad vendida con exito!");
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
        ArrayList<Propiedad> listaDePropiedades = this.barrios.get(propiedad.getBarrio()).getPropiedades();
        for (Propiedad propiedadLista : listaDePropiedades) {
            int dif = Math.abs(propiedad.getConstrucciones().ordinal()- 1 - propiedadLista.getConstrucciones().ordinal()) ;
            if (dif > 1) {
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
            int dif = propiedad.getConstrucciones().ordinal() - listaDePropiedades.get(i).getConstrucciones().ordinal();
            if (dif > 0){
                System.out.println("ERROR: EL JUGADOR "+jugador.getNombre()+ " NO PUEDE CONSTUIR DEBIDO A QUE EXCEDE EL LIMITE DE DIFERENCIA ENTRE LAS CONTRUCCIONES DE LAS PROPIEDADES");
                return  false;
            }
        }
        return true;
    }

    public void construirEnPropiedad(Jugador jugador,Propiedad propiedad){
        if (validarConstruccion(jugador, propiedad.getBarrio(),propiedad)){
            propiedad.sumarConstruccion();
            propiedad.actualizarAlquiler();
            jugador.restarPlata(propiedad.getPrecioCasa());
            jugador.sumarAlPatrimonio(propiedad.getPrecioCasa()* Constantes.PORCENTAJE_DE_VENTA);
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

    public void deconstruirCasa(Barrio barrio, Jugador jugador,Propiedad propiedad){
            jugador.restarPatrimonio(propiedad.getPrecioCasa());
            propiedad.restarConstruccion();
            propiedad.actualizarAlquiler();
            System.out.println("Propiedad fue reducida a "+propiedad.getConstrucciones());
    }
}
