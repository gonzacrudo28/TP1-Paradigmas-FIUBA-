package org.example.controller;

import org.example.funciones.FuncionesExtras;
import org.example.model.Estado;
import org.example.model.tipoCasilleros.Casillero;
import org.example.model.Jugador;


public class PagarFianza  {
    private FuncionesExtras funcionesExtras;
    public PagarFianza(FuncionesExtras funcionesExtras) {
        this.funcionesExtras = funcionesExtras;
    }
    public void ejecutar(Jugador jugador, Casillero carcel){
        double fianza = carcel.getFianza();
        if (comprobarPago(carcel.getFianza(),jugador.getPlata())){
            jugador.restarPlata(fianza);
            jugador.quedaLibre();
            System.out.println("El jugador "+ jugador.getNombre() + " ha pagado la fianza!\nLe quedan $"+ jugador.getPlata());
        }else{
            jugador.restarCondena();
            System.out.println("NO PUEDE PAGAR LA FIANZA, PLATA INSUFICIENTE");
        }
    }
    public boolean comprobarPago(double fianza, double dinero){
        return fianza <= dinero;
    }
}

