package org.example.controller;

public class CheckNombres {
    public void checkNombres(String nombre) {
        String[] nombres = nombre.split(" ");
        if (nombres.length < 2 || nombres.length > 4) {
            System.out.println("La cantidad de nombres no es correcta");
            System.exit(0);
        }
    }
}
