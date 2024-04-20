package org.example.controller;

import org.example.model.*;
import org.example.model.tipoCasilleros.Casillero;
import org.example.model.tipoCasilleros.DePropiedad;
import org.example.model.tipoCasilleros.TipoCasillero;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.TerminalBuilder;
import org.jline.terminal.Terminal;
import org.example.view.JuegoView;
import org.example.view.TableroView;
import java.io.IOException;
import java.util.List;

import org.example.controller.Constantes;

import java.io.IOException;
import java.util.Scanner;

public class JuegoController {
    private final Juego juego;
    private JuegoView vistaJuego;
    private LineReader reader;
    private AdministradorDeMovimientos administradorDeMovimientos;
    private Tablero tablero;

    public JuegoController(Juego juego) throws IOException {
        System.out.println("Iniciando Juego");
        this.juego = juego;
        this.tablero = juego.getTablero();
        this.vistaJuego = new JuegoView(juego);
        this.administradorDeMovimientos = new AdministradorDeMovimientos(juego.getTablero());
        Terminal terminal = TerminalBuilder.terminal();

        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
    }
    public boolean jugarTurnoPreso(Jugador jugador){
        if(jugador.getEstado().equals(Jugador.Estado.Preso)){
            // mostrar la opcion de pagar fianza
            if (jugador.getEstado().equals(Jugador.Estado.EnJuego)) {//and accion == No Pago)
                //Ya la pagó, entonces vuelvo a jugar
                return true;
            }
            return false;
        }
        return true;
    }
    public void jugarTurno() throws IOException {
        vistaJuego.muestaJugadoresInicial();
        juego.cambiarTurno();
        Jugador jugador = juego.getJugadorActual();
        if (!jugarTurnoPreso(jugador)){
            //el jugador decidio no pagar la fianza asi que tira para ver si puede salir (sale si saca
            //un numero>condena restante (toda esa logica la hace la funcion queda libre)
            int dados = juego.tirarDados();
            jugador.quedaLibre(dados);
        }
        int dados = juego.tirarDados();
        System.out.println("Es el turno de " + jugador.getNombre() + "\n" + "Tus dados son: " + dados + "\n");
        int casillaActual = administradorDeMovimientos.avanzarJugador(jugador, dados);
        System.out.println("Usted esta en el casillero: " + casillaActual + "\n");
        int numeroElecto = 1;
        vistaJuego.mostrar();

            Acciones acciones = new Acciones();
            acciones.mostrarAcciones();
        while (numeroElecto != 0) {
            String accion = reader.readLine("Seleccione la accion que quiere realizar indicando su numero (NUMERO):\n");
            numeroElecto = corroboroAccion(accion);
            if (numeroElecto == Constantes.NEGATIVO) {
                System.out.println("Accion inexistente\n");
            } else {
                Acciones.Accion accionElecta = acciones.getAccion(numeroElecto);

                if (accionElecta == null) {
                    System.out.println("Accion inexistente");// no hace falta pero lo dejo porlas
                }
                ejecutarAccion(accionElecta,jugador);
            }
        }

    }

    public int corroboroAccion(String accion) {
        CheckStrToInt checkStrToInt = new CheckStrToInt();
        return checkStrToInt.checkStringToInt(accion);
    }

    public void ejecutarAccion(Acciones.Accion accionElecta, Jugador jugador) {
        CheckStrToInt checkStrToInt = new CheckStrToInt();
        if (accionElecta == Acciones.Accion.CONSTRUIR) {
            String casillero = reader.readLine("Seleccione el casillero en que se encuentra la porpiedad(NUMERO):");
            int propiedad = (checkStrToInt.checkStringToInt(casillero));
                Propiedad prop = obtenerPropiedadJugador(propiedad,jugador);
                if (prop != null) {
                    jugador.reformarPropiedad(prop);
            }
        } else if (accionElecta == Acciones.Accion.VENDER) {
            String casillero = reader.readLine("Seleccione el casillero en que se encuentra la porpiedad(NUMERO):");
            int propiedad = (checkStrToInt.checkStringToInt(casillero));
                Propiedad prop = obtenerPropiedadJugador(propiedad,jugador);
                if (prop != null) {
                    jugador.venderPropiedad(prop);
                }

        } else if (accionElecta == Acciones.Accion.HIPOTECAR) {
            String casillero = reader.readLine("Seleccione el casillero en que se encuentra la porpiedad(NUMERO):");
            int propiedad = (checkStrToInt.checkStringToInt(casillero));
                Propiedad prop = obtenerPropiedadJugador(propiedad,jugador);
                if (prop != null) {
                    jugador.hipotecarPropiedad(prop);

            }
        } else if (accionElecta == Acciones.Accion.PAGAR_FIANZA) {
            jugador.pagarFianza();
        } else if (accionElecta == Acciones.Accion.DESHIPOTECAR) {
            String casillero = reader.readLine("Seleccione el casillero en que se encuentra la porpiedad(NUMERO):");
            int propiedad = (checkStrToInt.checkStringToInt(casillero));
            Propiedad prop = obtenerPropiedadJugador(propiedad,jugador);
            if (prop != null) {
                jugador.deshipotecarPropiedad(prop);
            }
        }else if (accionElecta == Acciones.Accion.COMPRAR) {
            Propiedad prop = obtenerPropiedad(jugador.getUbicacion());
                jugador.comprarPropiedad(prop,jugador);
        }
    }

    public boolean esPropiedad(int casillero) {
        if (casillero < tablero.getCantidadCasilleros()) {
            TipoCasillero tipoCasillero = tablero.getTipoCasillero(casillero);
            return tipoCasillero == TipoCasillero.PROPIEDAD;
        }
        return false;
    }
    public Propiedad obtenerPropiedadJugador(int casillero,Jugador jugador) {
        if (esPropiedad(casillero)) {
            List<Propiedad> propiedadList = jugador.getPropiedades();
            for (Propiedad propiedad : propiedadList) {
                if (propiedad.getUbicacion() == casillero) {
                    return propiedad;
                }
            }
            System.out.println("Esa propiedad no te pertenece");
            return null;
        }
        System.out.println("Accion imposible de realizar");
        return null;
    }

    public Propiedad obtenerPropiedad(int casillero) {
        if (esPropiedad(casillero)) {
            DePropiedad casilleroPropiedad = tablero.getPropiedad(casillero);
            return casilleroPropiedad.getPropiedad();
        }
        System.out.println("Accion imposible de realizar");
        return null;
    }
}



