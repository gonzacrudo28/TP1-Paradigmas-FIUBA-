package org.example.model;


import org.example.controller.Constantes;
import org.example.controller.ConstruccionController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// Enum EstadoPropiedad

// Clase Propiedad
public class Propiedad extends Comprable {

    private int numeroDeBarrio;
    private Construcciones construcciones;
    private int precioCasa;



    public Propiedad(int precio, int numeroDeBarrio,int ubicacion) {
        super(precio, ubicacion);
        this.numeroDeBarrio = numeroDeBarrio;
        this.construcciones = Construcciones.SIN_CASA;
        this.precioCasa = (int)(precio*Constantes.PORCENTAJE_PRECIO_CASA);


    }

    public enum Construcciones {
        SIN_CASA, UNA_CASA, DOS_CASAS, TRES_CASAS, HOTEL;
    }

    public int getPrecioCasa() {
        return precioCasa;
    }


    public String getNombrePropietario(){
        return propietario.getNombre();
    }
    public int getBarrio() { return numeroDeBarrio; }

   public void setPropietario(Jugador propietario) {
        if(this.propietario == null){
            this.propietario = propietario;
            this.estado = EstadoPropiedad.COMPRADO;
            propietario.agregarPropiedad(this);
            System.out.println("Propiedad comprada con exito");
            propietario.restarPlata((int)precio);
            return;
        }
        System.out.println("La propiedad ya fue comprada");
    }

    public Construcciones getConstrucciones(){
        return this.construcciones;
    }

    private Construcciones getSiguienteConstruccion(Construcciones construccionActual) {
        if (construcciones == Construcciones.HOTEL) {
            return Construcciones.HOTEL;
        }else {
            return siguiente(construccionActual);
        }
        }

    public Construcciones siguiente(Construcciones construccionActual){
        List<Construcciones> construccionesLista = Arrays.asList(Construcciones.values());
        return construccionesLista.get(construccionesLista.indexOf(construccionActual)+1);
    }

    public boolean validarPropietario(Jugador propietario){
        return propietario == this.getPropietario();
    }

    public void hipotecar (){
        estado = EstadoPropiedad.HIPOTECADO;
    }

    public void deshipotecar () {
        estado = EstadoPropiedad.COMPRADO;
    }

    public void liberar(){
        this.construcciones = Construcciones.SIN_CASA;
        this.propietario = null;
        this.estado = EstadoPropiedad.EN_VENTA;
        this.alquiler = setPrecioBaseAlquiler();
    }



    public void mejorarPropiedad(Barrio barrio, Jugador jugador){
        ConstruccionController construccionController = new ConstruccionController(this,barrio);

        if (construccionController.validarConstruccion(jugador)){
            this.construcciones = this.getSiguienteConstruccion(this.construcciones);
            actualizarAlquiler();
            System.out.println("Propiedad mejorada a "+this.getConstrucciones()+" con exito");
        }
    }

    public void vender(){
        // Falta chequear que haya vendido todas las construcciones -G
        double precioReventa = this.precio * Constantes.PORCENTAJE_DE_VENTA;
        int plataVentaCasas = venderPropiedades();
        this.propietario.sumarPlata((int)precioReventa + plataVentaCasas);
        this.liberar();
    }

    private void actualizarAlquiler(){
        this.alquiler = (int)(alquiler*Constantes.PORCENTAJE_ALQUILER_NUEVA_CASA) + alquiler;
    }

    private int venderPropiedades(){
        List<Construcciones> tiposConstrucciones = Arrays.asList(Construcciones.values());
        int cantPropiedades = tiposConstrucciones.indexOf(construcciones);
        return cantPropiedades*precioCasa;
    }

    private int setPrecioBaseAlquiler(){
        return (int)(precio*Constantes.PORCENTAJE_ALQUILER);
    }
}



