package org.example.controller;

import org.example.model.Jugador;
import org.example.model.Tablero;

//    TERMINAR_TURNO, CONSTRUIR,CONSULTAR_PRECIO_CASA,VENDER,HIPOTECAR,
//    COMPRAR, PAGAR_FIANZA, DESHIPOTECAR;
public interface ListaAcciones {
    void construir(Jugador jugador, int propiedad, Tablero tablero, ConstruccionController controller);
    void consultar_precio_casa(Jugador jugador, int propiedad, Tablero tablero, ConstruccionController controller);
    void vender(Jugador jugador, int propiedad, Tablero tablero, ConstruccionController controller);
    void hipotecar(Jugador jugador, int propiedad, Tablero tablero, ConstruccionController controller);
    void comprar(Jugador jugador, int propiedad, Tablero tablero, ConstruccionController controller);
    void pagar_fianza(Jugador jugador, int propiedad, Tablero tablero, ConstruccionController controller);
    void deshipotecar(Jugador jugador, int propiedad, Tablero tablero, ConstruccionController controller);
}


