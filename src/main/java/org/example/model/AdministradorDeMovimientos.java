package org.example.model;

public class AdministradorDeMovimientos {
    private final int cantCasillas;

    public AdministradorDeMovimientos(int cantCasillas){
        this.cantCasillas = cantCasillas;
    }

    private boolean pasaPorSalida(Jugador jugador, int tiradaDados){
    return (jugador.getUbicacion() + tiradaDados) >= cantCasillas;
    }

    public int avanzarJugador(Jugador jugador,int dados){
        int posNueva = jugador.getUbicacion() + dados;
        if (pasaPorSalida(jugador, dados)){
            posNueva -= cantCasillas;
            jugador.setUbicacion(posNueva);
            return posNueva;
        }else{
            jugador.setUbicacion(posNueva);
            return posNueva;
        }

    }

}
