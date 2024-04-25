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
    private ArrayList<EstacionTransporte> estaciones;
    private ArrayList<Propiedad> propiedades;
    private Estado estado;
    private int condena;
    private double patrimonio;

    public Jugador(String nombre) {
        this.ubicacion = 0;
        this.nombre = nombre;
        this.estado = Estado.EnJuego;
        this.propiedades = new ArrayList<>();
        this.condena = 0;
        this.estaciones = new ArrayList<>();
        this.patrimonio = 0;
    }

    //mover a algun ejecutador de acciones
    public void pagarFianza(double fianza){
       if (condena != 0){
           restarPlata(fianza);
           condena = 0;
           System.out.println("Fianza pagada con exito");
           this.estado = Estado.EnJuego;
           return;
        }
        System.out.println("No estas en la carcel!");
    }

    public void sumarAlPatrimonio(double monto){
        this.patrimonio += monto;
    }
    public void agregarPropiedad(Propiedad propiedad){
        propiedades.add(propiedad);
    }
    public void eliminarPropiedad(Propiedad propiedad){
        propiedades.remove(propiedad);
    }

    public void agregarEstacion(EstacionTransporte estacion){estaciones.add(estacion);}

    public void hipotecarPropiedad(Barrio barrio,Propiedad propiedad){
        propiedad.hipotecar(barrio,this);

    }
    public void restarPatrimonio(double monto){
        this.patrimonio -= monto;
    }

    public ArrayList<EstacionTransporte> getEstaciones() {
        return estaciones;
    }

    public void deshipotecarPropiedad(Propiedad propiedad){
        propiedad.deshipotecar();
        this.restarPlata((propiedad.getPrecio()* Constantes.PORCENTAJE_DE_DESHIPOTECAR));
        sumarAlPatrimonio(propiedad.getPrecio());
    }

    public void comprarComprable(Comprable comprable, Jugador jugador){
        double precioComprable = comprable.getPrecio();
        if (this.plata >= precioComprable) {
            comprable.setPropietario(jugador);
            sumarAlPatrimonio(precioComprable);
        }else{
            System.out.println("No se puede comprar propiedad");
        }
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
    public double getPatrimonio(){return this.patrimonio;}

    public void restarPlata(double dinero){
        if (plata > dinero){
            plata -= dinero;
            return;
        }else{
            System.out.println("No tienes dinero suficiente para comprar esta propiedad \nObs! Si quieres puedes hipotecar otra propiedad para comprar esta");
        }
    }

    public boolean puedePagarlo(double dinero){
        return dinero <= this.patrimonio + this.plata;
    }

    public void perder(){
        //enganchar en algun lado con que la posicion de los casilleros de sus propiedades
        //se conviertan en props de paso
        this.setEstado(Estado.Quiebra);
        for (Propiedad propiedades : this.propiedades) {
            propiedades.liberar();
        }
    }
    public void restarCondena(){
        this.condena--;
    }

    public void sumarPlata(double dinero){this.plata += dinero;}

    public void setUbicacion(int ubicacion){ this.ubicacion = ubicacion; }

    public boolean estaEnQuiebra(){return Estado.Quiebra.equals(this.estado);}

    public void venderPropiedad(Propiedad propiedad){
        if (propiedad.getPropietario().equals(this)){
            propiedad.venderComprable();
            restarPatrimonio(propiedad.getPrecio());
            eliminarPropiedad(propiedad);
            return;
        }
        System.out.printf("%s no es el propietario, el dueño es %s", this.nombre, propiedad.getPropietario());
    }

    public void quedaLibre(){
        this.setCondena(0);
        this.estado = Estado.EnJuego;
    }

    public void eliminarEstacion(EstacionTransporte estacionTransporte) {
        estaciones.remove(estacionTransporte);
    }

    public void vender(Comprable comprable){
        comprable.venderComprable();
        restarPatrimonio(comprable.getPrecio());
    }

    public boolean gano(){
        for (Propiedad propiedad: propiedades){
            if (!propiedad.tieneHotel()){
                return false;
            }
        }
        return true;
    }

}

