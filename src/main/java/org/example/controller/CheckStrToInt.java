package org.example.controller;

public class CheckStrToInt {
    protected int checkStringToInt(String str){
        try {
            int num = Integer.parseInt(str);
            return num;
        }catch (NumberFormatException e){
            return Constantes.NEGATIVO;
        }
    }
}

