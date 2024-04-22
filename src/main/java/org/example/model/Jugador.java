package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Jugador{
    private final String nombre;
    private  Colores.Color color;
    private int plata;
    private int ubicacion;
    private List<Propiedad> propiedades;
    private Estado estado;
    private int condena;


    //mover a algun ejecutador de acciones
    public void pagarFianza(){
        if(condena != 0){
            //restarPlata(Configuracion.getFianza());
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

    public void reformarPropiedad(Propiedad propiedad){
        propiedad.mejorarPropiedad();
    }

    public void hipotecarPropiedad(Propiedad propiedad){
        propiedad.hipotecar();
        // //sumar valor de la propiedad al jugador
    }

    public void deshipotecarPropiedad(Propiedad propiedad){
        propiedad.deshipotecar();
        this.restarPlata((int)(propiedad.getPrecio()*0.7));
    }
    /*

    COMPRAR PROP Y COMPRAR ESTACION UNIFICADOS -> COMPRARCOMPRABLE

    public void comprarPropiedad(Propiedad propiedad,Jugador jugador){
        int precioPropiedad = (int)propiedad.getPrecio();
        System.out.println(precioPropiedad);
        if (this.plata >= precioPropiedad) {
            propiedad.setPropietario(jugador);
        }else{
            System.out.println("No se puede comprar propiedad");
        }
    }
    public void comprarEstacion(Comprable estacion, Jugador jugador) {
        int precioEstacion = (int)estacion.getPrecio();
        System.out.println(precioEstacion);
        if (this.plata >= precioEstacion) {
            estacion.setPropietario(jugador);
        }else{
            System.out.println("No se puede comprar propiedad");
        }
    }
*/

    public void comprarComprable(Comprable comprable, Jugador jugador){
        int precioComprable = comprable.getPrecio();
        System.out.println(precioComprable);
        if (this.plata >= precioComprable) {
            comprable.setPropietario(jugador);
        }else{
            System.out.println("No se puede comprar propiedad");
        }
    }

    public enum Estado{
        EnJuego,Preso,Quiebra,Perdio
    }
    public Jugador(String nombre) {
        this.ubicacion = 0;
        this.nombre = nombre;
        this.estado = Estado.EnJuego;
        this.propiedades = new ArrayList<>();
        this.condena = 0;
    }

    public void setPlata(int plata) {
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
    public int getPlata() {return plata;}
    public int getUbicacion() {return ubicacion;}
    public int getCondena(){
        return this.condena;
    }
    public Colores.Color getColor() {return this.color;}
    public List<Propiedad> getPropiedades(){return this.propiedades;}


    public void restarPlata(int dinero){
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

    public void sumarPlata(int dinero){this.plata += dinero;}

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

    public void quedaLibre(int dados){
        if (dados > condena){
            this.setCondena(0);
            this.estado = Estado.EnJuego;

            System.out.println("Jugador LIBRE por tirada dados mayor a condena");
        }
        this.restarCondena();
    }


}

