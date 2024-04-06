package org.example.controller;

public class CheckStrToInt {
    protected boolean checkStringToInt(String str){
        try {
            int num = Integer.parseInt(str);
            return num > 0;
        }catch (NumberFormatException e){
            return false;
        }
    }
}

