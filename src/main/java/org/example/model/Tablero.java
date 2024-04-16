package org.example.model;

import org.example.controller.Constantes;
import org.example.model.tipoCasilleros.*;
import org.example.model.tipoCasilleros.Estacion;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Tablero {
    private Casillero[] casilleros;
    private int cantidadDeCasilleros;
    private List<Barrio> barrios;

    public Tablero(int cantidadDeCasilleros,int precioMulta,int precioVuelta,int turnosPreso) {
        this.cantidadDeCasilleros = cantidadDeCasilleros;
        this.casilleros = new Casillero[cantidadDeCasilleros];
        this.barrios = new ArrayList<Barrio>();
        crearMapa();
    }

    private void crearMapa(){
        posicionarCasillerosBase();
        crearRestoDeCasilleros();
    }

    public Casillero getCasillero(int posicion){
        return casilleros[posicion];
    }
    public TipoCasillero getTipoCasillero(int posicion){
        return casilleros[posicion].getTipo();
    }

    private void crearRestoDeCasilleros(){
        // ESTO NO ES LA FORMA MAS EFICIENTE PERO DESPUES SE CAMBIA, POR LO MENOS SE ESTA CREANDO EL TABLERO
        Random random = new Random();
        int contadorDePropiedades = 0;
        int cantidadDeCasillerosTransportes = (int)(cantidadDeCasilleros*Constantes.PORCENTAJE_DE_TRANSPORTE);
        int contadorDeCasillerosExtra= 3;
        Barrio nuevoBarrio = null;
        for(int  numeroCasillero = 0;  numeroCasillero < this.cantidadDeCasilleros;  numeroCasillero++){
            if (this.casilleros[numeroCasillero] == null){
                if (contadorDePropiedades == Constantes.CANTIDAD_CASAS_POR_BARRIO){
                    if (contadorDeCasillerosExtra == 3 && cantidadDeCasillerosTransportes != 0){
                        this.casilleros[numeroCasillero]= new Estacion();
                        contadorDeCasillerosExtra = 0;
                    }else{
                        if (random.nextBoolean()) {
                            this.casilleros[numeroCasillero] = new DeLoteria();
                        }else{
                            this.casilleros[numeroCasillero] = new DeMulta();
                        }
                    }
                    contadorDePropiedades= 0;
                    contadorDeCasillerosExtra ++;
                }else{
                    if (contadorDePropiedades == 0){
                        nuevoBarrio = new Barrio("Color");
                        this.barrios.add(nuevoBarrio);
                    }
                    DePropiedad casilleroDePropiedad = new DePropiedad(numeroCasillero);
                    nuevoBarrio.addCasillero(casilleroDePropiedad);
                    this.casilleros[numeroCasillero] = casilleroDePropiedad;
                    contadorDePropiedades++;
                }
            }
        }
    }


    private void posicionarCasillerosBase(){
        int posCarcel = (int) (this.cantidadDeCasilleros*Constantes.POS_CARCEL);
        int posDePaso= (int) (this.cantidadDeCasilleros*Constantes.POS_DE_PASO);
        int posIrACarcel= (int)(this.cantidadDeCasilleros*Constantes.POS_IR_CARCEL);
        this.casilleros[Constantes.POS_LLEGADA_SALIDA]= new LlegadaPartida();
        this.casilleros[posCarcel]= new Carcel();
        this.casilleros[posIrACarcel] = new IrALaCarcel();
        this.casilleros[posDePaso] = new DePaso();
    }

    public int getCantidadCasilleros() {
        return this.cantidadDeCasilleros;
    }


}
/*
FALTA EN TABLERO (QUE LO HACE TOP)

CUANDO SE CREA EL BARRIO QUE VAYA CAMBIANDO DE COLOR


*/