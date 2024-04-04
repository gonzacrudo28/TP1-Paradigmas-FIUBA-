package org.example.model;
import org.example.model.Tablero;
import org.example.model.Colores;
import org.example.model.Banco;
import java.util.List;
public class Jugador{
    private final String nombre;
    private final Colores.Color color;
    private int plata;
    private int ubicacion;
    private List<Propiedad> propiedades;
    private Estado estado;
    private int condena;

    public  enum Estado{
        EnJuego,Preso,Deuda, Perdio
    }
    public Jugador(String nombre, Colores.Color color, int plata, int ubicacion, List<Propiedad> propiedades) {
        this.nombre = nombre;
        this.color = color;
        this.plata = plata;
        this.ubicacion = ubicacion;
        this.propiedades = propiedades;
        this.estado = Estado.EnJuego;
    }
    public String getNombre() {
        return this.nombre;}
    public void setEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
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

    public void setPlata(int plata) {
        this.plata = plata;
    }
    public void setCondena(int condena){this.condena += condena;}
    public boolean restarPlata(int dinero){
        if (this.plata > dinero){
            this.plata -= dinero;
            return True;
        }
        System.out.println("Ups!" + jugador.getNombre() + "no tiene dinero suficiente para pagar esta deuda");
        //Agregar parte de Controller config
        return False;
    }
    public void sumarPlata(int dinero){
        this.plata += dinero;
    }

    public void setUbicacion(int ubicacion, Banco banco) {
        // Falta pasarle la cantidad de casilleros :D
        // Falta pasarle la cantidad de $ por pasar dar la vuelta :D

        // validar que el player no este preso (main).
        if(this.ubicacion + ubicacion <= CANT_CASILLEROS){
            this.ubicacion += ubicacion;
            return;
        }
        int bono = 100;
        this.ubicacion = (this.ubicacion + ubicacion - CANT_CASILLEROS);
        banco.pagarBono(jugador, bono);
        // if ubicacion nueva es menos a ubicacion vieja, pagar bono (main).
    }
    //NICO: ESTO ESTA MAL DEBERIAMOS LLAMAR A UNA FUNCION AUXILIAR AVANZAR Y DE AHI OTRA AUXILIAR(DENTRO DE ADMINISTRADOR DE MOVIMIENTOS) QUE SE ENCARGUE DE CHECKEAR SI YA DIO LA VUELTA O NO
    // ADEMAS SI SE ENCARGA DE SETEAR LA UBIC NO PUEDE COBRAR LO DEL BANCO Y ESO
    public int avanzarJugador(int tiradaDados){
        // En esta func hay que poner la nueva posicion post haber hecho los chequeos
        return -1;
    }

    public boolean estaEnQuiebra(Jugador jugador){
        return Estado.Deuda.equals(jugador.getEstado());
    }

    public void perder(Jugador jugador, List<Jugador> jugadores){
        //faq: si llamas a perder, se deberia chequear antes que el pl esté en quiebra
        if(estaEnQuiebra(jugador)){
            for (int i=0; i < jugador.propiedades.size(); i++){
                Propiedad propiedad = jugador.propiedades.get(i);
                propiedad.liberarPropiedad(jugador);
            }
            jugadores.remove(jugador); // faq: hace falta? para mi lo solucionamos con ignorarlo en los turnos

        }
    }

}

