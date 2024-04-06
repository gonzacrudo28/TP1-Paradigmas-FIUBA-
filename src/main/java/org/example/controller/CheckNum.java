package org.example.controller;
public class CheckNum {
public void checkNumeros(String cantidad) {
    CheckStrToInt checkStrToInt = new CheckStrToInt();
    boolean bool = checkStrToInt.checkStringToInt(cantidad);
    if (!bool){
        System.out.println("Esa cantidad no es posible");
        System.exit(0);
    }
}
}