package org.example.model;
import org.example.model.Tablero;
import org.example.model.Colores;
import org.example.model.Banco;

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
    public void setCondena(int condena){this.condena += condena;}



    public String getNombre() {
        return this.nombre;
    }
    public Estado getEstado(){
        return this.estado;
    }
    public int getPlata() {
        return plata;
    }
    public int getUbicacion() {
        return ubicacion;
    }
    public int getCondena(){
        return this.condena;
    }
    public Colores.Color getColor() {
        return this.color;
    }


    public boolean restarPlata(int dinero){
        if (this.plata > dinero){
            this.plata -= dinero;
            return true;
        }
        System.out.println("Ups!" + this.nombre + "no tiene dinero suficiente para pagar esta deuda");
        //Agregar parte de Controller config
        return false;
    }
    public void sumarPlata(int dinero){
        this.plata += dinero;
    }

    public int avanzarJugador(int dados){
        this.ubicacion += dados;
        return this.ubicacion;}

    public void setUbicacion(int ubicacion){ this.ubicacion = ubicacion; }

    public boolean estaEnQuiebra(){
        return Estado.Quiebra.equals(this.estado);
    }

    // antes de llamar este metodo ya habria que chequear si esta en quiebra para hacerlo perder
//    public void perder(Jugador jugador, List<Jugador> jugadores){
//        for (int i=0; i < jugador.propiedades.size(); i++){
//            Propiedad propiedad = jugador.propiedades.get(i);
//            propiedad.liberarPropiedad(jugador);
//        }
//        jugador.setEstado(Estado.Quiebra);
//        //jugadores.remove(jugador); // faq: hace falta? para mi lo solucionamos con ignorarlo en los turnos
//    }

}

