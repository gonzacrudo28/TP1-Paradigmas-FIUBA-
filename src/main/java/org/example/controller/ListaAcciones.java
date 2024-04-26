package org.example.controller;

import org.example.model.Jugador;

public interface ListaAcciones {
    void construir(Jugador jugador, int propiedad, ConstruccionController controller);
    void consultar_precio_casa(Jugador jugador, int propiedad, ConstruccionController controller);
    void vender(Jugador jugador, int propiedad, ConstruccionController controller);
    void hipotecar(Jugador jugador, int propiedad, ConstruccionController controller);
    void comprar(Jugador jugador, int propiedad, ConstruccionController controller);
    void deshipotecar(Jugador jugador, int propiedad,  ConstruccionController controller);
}


