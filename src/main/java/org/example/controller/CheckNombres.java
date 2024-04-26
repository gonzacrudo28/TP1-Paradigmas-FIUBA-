package org.example.controller;

public class CheckNombres {
    public boolean checkNombres(String nombre) {
        String[] nombres = nombre.split(" ");
        if (nombres.length < 2 || nombres.length > 4) {
            System.out.println("La cantidad de nombres no es correcta");
            return false;
        }
        return true;
    }
}
