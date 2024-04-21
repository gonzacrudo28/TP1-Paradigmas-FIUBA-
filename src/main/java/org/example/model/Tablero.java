package org.example.model;

import org.example.controller.Constantes;
import org.example.model.tipoCasilleros.*;
import org.example.model.tipoCasilleros.Estacion;
import org.example.controller.Configuracion;


import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Tablero {
    private Casillero[] casilleros;
    private int cantidadDeCasilleros;
    private List<Barrio> barrios;
    private Configuracion configuraciones;

    public Tablero(Configuracion configuraciones) {
        this.cantidadDeCasilleros = configuraciones.getCantidadCasilleros();
        this.casilleros = new Casillero[cantidadDeCasilleros];
        this.barrios = new ArrayList<Barrio>();
        this.configuraciones = configuraciones;
        crearMapa();
    }

    public DePropiedad getPropiedad(int numeroCasilla) {
        return (DePropiedad) this.casilleros[numeroCasilla];
    }
    private void crearMapa(){
        posicionarCasillerosBase();
        crearRestoDeCasilleros(configuraciones);
    }

    public Casillero getCasillero(int posicion){
        return casilleros[posicion];
    }
    public TipoCasillero getTipoCasillero(int posicion){
        return casilleros[posicion].getTipo();
    }

    private void crearRestoDeCasilleros(Configuracion configuraciones){
        Random random = new Random();
        int contadorDePropiedades = 0;
        int contadorBarrios = 1;
        int cantidadDeCasillerosTransportes = (int)(cantidadDeCasilleros*Constantes.PORCENTAJE_DE_TRANSPORTE);
        int contadorDeCasillerosExtra= 3;
        double precioBarrio = (Constantes.PORCENTAJE_AUMENTO_BARRIO*contadorBarrios);
        double precioTransporte = Constantes.PORCENTAJE_PRECIO_TRANSPORTE*configuraciones.getMontoInicial();
        Barrio nuevoBarrio = null;
        for(int  numeroCasillero = 0;  numeroCasillero < this.cantidadDeCasilleros;  numeroCasillero++){
            System.out.println("CONTADOR BARRIO"+contadorBarrios);
            if (this.casilleros[numeroCasillero] == null){
                if (contadorDePropiedades == Constantes.CANTIDAD_CASAS_POR_BARRIO){
                    if (contadorDeCasillerosExtra == 3 && cantidadDeCasillerosTransportes != 0){
                        this.casilleros[numeroCasillero]= new Estacion(numeroCasillero,precioTransporte);
                        contadorDeCasillerosExtra = 0;
                    }else{
                        if (random.nextBoolean()) {
                            this.casilleros[numeroCasillero] = new DeLoteria(numeroCasillero);
                        }else{
                            this.casilleros[numeroCasillero] = new DeMulta(numeroCasillero,configuraciones.getMontoMulta());
                        }
                    }
                    contadorDePropiedades = 0;
                    contadorDeCasillerosExtra ++;
                }else{
                    if (contadorDePropiedades == 0){
                        precioBarrio = (Constantes.PORCENTAJE_AUMENTO_BARRIO*contadorBarrios);
                        nuevoBarrio = new Barrio(contadorBarrios,precioBarrio);
                        this.barrios.add(nuevoBarrio);
                        contadorBarrios++;
                        System.out.println("BARRIO NUMERO: M " + nuevoBarrio.getNumeroBarrio());
                    }
                    //NICO: VER SI ESTO NO LO PODRIA HACER EL BARRIO
                    System.out.println("PRECIO"+(precioBarrio*configuraciones.getMontoInicial())+"PRECIO INT"+ (int)(precioBarrio*configuraciones.getMontoInicial()));
                    DePropiedad casilleroDePropiedad = new DePropiedad(numeroCasillero,(int)(precioBarrio*configuraciones.getMontoInicial()),contadorBarrios);
                    nuevoBarrio.addCasillero(casilleroDePropiedad);

                    this.casilleros[numeroCasillero] = casilleroDePropiedad;
                    contadorDePropiedades++;
                }
            }
        }
    }


    //LOS PRECIO DE LOS BARRIOS VAN DE 0.025*

    private void posicionarCasillerosBase(){
        int posCarcel = (int) (this.cantidadDeCasilleros*Constantes.POS_CARCEL);
        int posDePaso= (int) (this.cantidadDeCasilleros*Constantes.POS_DE_PASO);
        int posIrACarcel= (int)(this.cantidadDeCasilleros*Constantes.POS_IR_CARCEL);
        this.casilleros[Constantes.POS_LLEGADA_SALIDA]= new LlegadaPartida(Constantes.POS_LLEGADA_SALIDA,configuraciones.getMontoVuelta());
        this.casilleros[posCarcel]= new Carcel(posCarcel,configuraciones.getMontoFianza(),configuraciones.getCondenaCarcel());
        this.casilleros[posIrACarcel] = new IrALaCarcel(posIrACarcel,configuraciones.getCondenaCarcel(),posCarcel);
        this.casilleros[posDePaso] = new DePaso(posDePaso);
    }

    public int getCantidadCasilleros() {
        return this.cantidadDeCasilleros;
    }

    public CasilleroEjecutable getCasilleroEjecutable(int numeroCasillero) {
        return (CasilleroEjecutable) this.casilleros[numeroCasillero];
    }

}
/*
FALTA EN TABLERO (QUE LO HACE TOP)
CUANDO PONES 10 PROPIEDADES NO CREA LA PRIMER PROPIEDAD COMO BARRIO SINO QUE SIGUE CON LAS SEGUNDA
CUANDO SE CREA EL BARRIO QUE VAYA CAMBIANDO DE COLOR
*/