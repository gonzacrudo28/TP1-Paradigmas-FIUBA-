package org.example.model;
//sender comunica, a traves de command, al receiver que ejecute tal accion
public class SenderAcciones {
    CommandAcciones commandAcciones;

    public void setCommand(CommandAcciones commandAcciones){
        this.commandAcciones = commandAcciones;
    }

    public void ejecutarCommand(){
        commandAcciones.ejecutar();
    }
}
