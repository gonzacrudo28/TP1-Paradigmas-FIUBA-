package org.example.model.tipoCasilleros;

import org.example.model.Colores;
import org.example.model.Jugador;
import org.example.model.Propiedad;

public class DePropiedad extends Casillero{
    Propiedad propiedad;
    int numeroDePropiedad;

    public DePropiedad(int numeroDePropiedad, int precio, int numeroDeBarrio) {
        this.efecto = "Propiedad";
        this.tipo = TipoCasillero.PROPIEDAD;
        this.numeroDePropiedad = numeroDePropiedad;
        this.propiedad = new Propiedad(precio, numeroDeBarrio, numeroDePropiedad);
    }
    public Propiedad getPropiedad() {
        return propiedad;
    }
}

/*
    private Propiedad propiedad;
    public DePropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public void CobrarAlquiler(Jugador jugador) {
        if (jugador != this.propiedad.getPropietario()){
            jugador.restarPlata((int)this.propiedad.getAlquiler());
        }
    }

    public void ComprarPropiedad(Jugador jugador) {
        this.propiedad.setPropietario(jugador);*/