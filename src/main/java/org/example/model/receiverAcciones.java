package org.example.model;
//accionador
// hay que implementar varios receiever segun el tipo  de funcion.
//ejemplo: receiver1 abarca construir/reformar (temas constructores)
//ejemplo2: receiver2: hipotecar/desipotecar/comprar/vender (temas inmobiliarios)
//etc
public class receiverAcciones {

    public void executeCommand(CommandAcciones command) {
        command.ejecutar();
    }

}
