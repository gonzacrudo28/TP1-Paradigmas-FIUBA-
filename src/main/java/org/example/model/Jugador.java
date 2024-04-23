package org.example.model;

import org.example.controller.Constantes;
import org.example.model.tipoCasilleros.Estacion;
import org.example.model.Estado;
import java.util.ArrayList;
import java.util.List;

public class Jugador{
    private final String nombre;
    private  Colores.Color color;
    private double plata;
    private int ubicacion;
    private ArrayList<Estacion> estaciones;
    private ArrayList<Propiedad> propiedades;
    private Estado estado;
    private int condena;


    //mover a algun ejecutador de acciones
    public void pagarFianza(double fianza){
       if (this.plata >= fianza){
           restarPlata(fianza);
           condena = 0;
           System.out.println("Fianza pagada con exito");
           this.estado = Estado.EnJuego;
           return;
        }
        System.out.println("No se puede pagar fianza");
    }



    public void agregarPropiedad(Propiedad propiedad){
        propiedades.add(propiedad);
    }

    public void reformarPropiedad(Barrio barrio,Propiedad propiedad){
        propiedad.mejorarPropiedad(barrio,this);
     }

    public void hipotecarPropiedad(Propiedad propiedad){
        propiedad.hipotecar();
        this.sumarPlata(propiedad.getPrecio());
    }

    public ArrayList<Estacion> getEstaciones() {
        return estaciones;
    }

    public void deshipotecarPropiedad(Propiedad propiedad){
        propiedad.deshipotecar();
        this.restarPlata((int)(propiedad.getPrecio()* Constantes.PORCENTAJE_DE_DESHIPOTECAR));
    }

    public void comprarComprable(Comprable comprable, Jugador jugador){
        int precioComprable = comprable.getPrecio();
        if (this.plata >= precioComprable) {
            comprable.setPropietario(jugador);
        }else{
            System.out.println("No se puede comprar propiedad");
        }
    }

    public Jugador(String nombre) {
        this.ubicacion = 0;
        this.nombre = nombre;
        this.estado = Estado.EnJuego;
        this.propiedades = new ArrayList<>();
        this.condena = 0;
        this.estaciones = new ArrayList<>();
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }
    public void setColor(Colores.Color color) {
        this.color = color;
    }
    public void setEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
    }
    public void setCondena(int condena){this.condena = condena;}

    public String getNombre() {return this.nombre;}
    public Estado getEstado(){
        return this.estado;
    }
    public double getPlata() {return plata;}
    public int getUbicacion() {return ubicacion;}
    public int getCondena(){
        return this.condena;
    }
    public Colores.Color getColor() {return this.color;}
    public ArrayList<Propiedad> getPropiedades(){return this.propiedades;}


    public void restarPlata(double dinero){
        if (plata > dinero){
            plata -= dinero;
            return;
        }
        System.out.println("Ups!" + this.nombre + "no tiene dinero suficiente para pagar");
        //Agregar parte de Controller config
    }

    public void restarCondena(){
        this.condena--;
    }

    public void sumarPlata(double dinero){this.plata += dinero;}

    public void setUbicacion(int ubicacion){ this.ubicacion = ubicacion; }

    public boolean estaEnQuiebra(){return Estado.Quiebra.equals(this.estado);}

    public void perder(Jugador jugador){
        jugador.setEstado(Estado.Quiebra);
        for (Propiedad propiedad: propiedades){
            propiedad.liberar();
        }
    }

    public void venderPropiedad(Propiedad propiedad){
        if (propiedad.getPropietario().equals(this)){
            propiedad.vender();
            return;
        }
        System.out.printf("%s no es el propietario, el dueño es %s", this.nombre, propiedad.getPropietario());
    }

    public void quedaLibre(){
        this.setCondena(0);
        this.estado = Estado.EnJuego;
    }

}

