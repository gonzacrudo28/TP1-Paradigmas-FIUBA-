package org.example.model;

import java.util.ArrayList;
import java.util.List;
import org.example.model.Propiedad;

public class Jugador{
    private final String nombre;
    private  Colores.Color color;
    private int plata;
    private int ubicacion;
    private List<Propiedad> propiedades;
    private Estado estado;
    private int condena;

    //ESTO ES PROVISIORIO, LO VOY A CAMBIAR A UNA NUEVA CLASE EJECUTOR DE ACCIONES
    //LAS FUNCIONES ESTAN HECHAS ASI NOMAS, FALTA PERFECCIONAR EN BASE AL TABLERO

    //mover a algun ejecutador de acciones
    public void pagarFianza(){
        if(condena != 0){
            restarPlata(Configuracion.getFianza());
            condena = 0;
            return;
        }
        System.out.println("No se puede pagar fianza");
    }

    public void comprarPropiedad(Propiedad propiedad){
        //buscar propiedad en el tablero con jugador.getUbicacion
       // Propiedad propiedad = tablero.getPropiedad(Jugador.getUbicacion);
        propiedad.setPropietario(Jugador);
    }


// estas dos tienen el mismo efecto
    public void contruirEnPropiedad(Propiedad propiedad){
        propiedad.mejorarPropiedad();
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
        //restar valor de la propiedad al jugador
    }

    public void ejecutarAccion(Acciones.Accion accionElecta) {
        if(accionElecta == Acciones.Accion.CONSTRUIR){
            contruirEnPropiedad();
        }
        else if(accionElecta == Acciones.Accion.REFORMAR){
            reformarPropiedad();
        }
        else if(accionElecta == Acciones.Accion.VENDER){
            venderPropiedad();
        }else if(accionElecta == Acciones.Accion.HIPOTECAR){
            hipotecarPropiedad();
        }else if(accionElecta == Acciones.Accion.PAGAR_FIANZA){
            pagarFianza();
        }else if(accionElecta == Acciones.Accion.DESHIPOTECAR){
            deshipotecarPropiedad();
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

    public void setUbicacion(int ubicacion){ this.ubicacion = ubicacion; }

    public boolean estaEnQuiebra(){
        return Estado.Quiebra.equals(this.estado);
    }

    public void perder(Jugador jugador){
        jugador.setEstado(Estado.Quiebra);
        for (Propiedad propiedad: propiedades){
            propiedad.liberar();
        }
    }

    public void venderPropiedad(Propiedad propiedad){
        propiedad.vender();
    }

}

