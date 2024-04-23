package org.example.controller;

import org.example.model.*;
import org.example.model.tipoCasilleros.*;
import org.fusesource.jansi.Ansi;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.TerminalBuilder;
import org.jline.terminal.Terminal;
import org.example.view.JuegoView;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;


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
            // mostrar la opcion de pagar fianza y terminar turno;
            Acciones acciones = new Acciones();
            acciones.getAccionesJugadorPreso();
           System.out.println("Maquinita estas en Canada, para pagar la fianza picale al 1 :) o 0 si queres probar suerte");
            int numeroElecto = 1;
            while (numeroElecto != 0){
                String accion = reader.readLine("Seleccione la accion que quiere realizar indicando su numero (NUMERO):\n");
                numeroElecto = corroboroAccion(accion);
                if ((numeroElecto != 0 && numeroElecto != 1)) {
                    System.out.println("Accion inexistente\n");
                } else {
                    Acciones.Accion accionElecta = acciones.getAccionPreso(numeroElecto);
                    ejecutarAccion(accionElecta,jugador);
                }
            }
        }
        return jugador.getEstado().equals(Jugador.Estado.EnJuego);
    }

    public void jugarTurno() throws IOException {
        vistaJuego.muestaJugadoresInicial();
        juego.cambiarTurno();
        Jugador jugador = juego.getJugadorActual();
        int dados = juego.tirarDados();
        boolean puedeJugar = false;
        if (!jugarTurnoPreso(jugador)){
            jugador.quedaLibre(dados);
            return;
        }
        Ansi colorANSI = null;
        Ansi resetColor = null;
        colorANSI = obtenerColorANSI(jugador.getColor());
        resetColor = Ansi.ansi().reset();
        System.out.println(colorANSI+"Es el turno de " + jugador.getNombre() + "\n" + "Tus dados son: " + dados + "\n");
        int casillaAnterior = jugador.getUbicacion();
        int casillaActual = administradorDeMovimientos.avanzarJugador(jugador, dados);
        System.out.println("Usted esta en el casillero: " + casillaActual + "\n" + resetColor);
        //deberia ir aca?
        if((casillaAnterior+dados) >= tablero.getCantidadCasilleros()) {
            juego.pagarBono(jugador);
        }

        Casillero casillero = tablero.getCasillero(casillaActual);
        if (casillero instanceof CasilleroEjecutable) {
            ejecutar(jugador,casillaActual);
        }
        int numeroElecto = 1;

        vistaJuego.mostrar();
            Acciones acciones = new Acciones();
            acciones.getAcciones(colorANSI,resetColor);
        while (numeroElecto != 0) {
            String accion = reader.readLine(colorANSI + "Seleccione la accion que quiere realizar indicando su numero (NUMERO):\n"+ resetColor );
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

    public void ejecutar(Jugador jugador, int ubicacionJugador){
        CasilleroEjecutable casillero = tablero.getCasilleroEjecutable(ubicacionJugador);
        casillero.ejecutarCasillero(jugador);
    }

    public int corroboroAccion(String accion) {
            CheckStrToInt checkStrToInt = new CheckStrToInt();
            return checkStrToInt.checkStringToInt(accion);
    }

    public void ejecutarAccion(Acciones.Accion accionElecta, Jugador jugador) {
            CheckStrToInt checkStrToInt = new CheckStrToInt();
            if (accionElecta == Acciones.Accion.CONSTRUIR) {
                String casillero = reader.readLine("Seleccione el casillero en que se encuentra la porpiedad (NUMERO):");
                int propiedad = (checkStrToInt.checkStringToInt(casillero));
                Propiedad prop = obtenerPropiedadJugador(propiedad,jugador);
                if (prop != null) {
                    jugador.reformarPropiedad(tablero.getBarrio(prop),prop);
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
                jugador.pagarFianza(juego.getFianza()); //ACA FALLA MAQUINAS
            } else if (accionElecta == Acciones.Accion.DESHIPOTECAR) {
                String casillero = reader.readLine("Seleccione el casillero en que se encuentra la porpiedad(NUMERO):");
                int propiedad = (checkStrToInt.checkStringToInt(casillero));
                Propiedad prop = obtenerPropiedadJugador(propiedad,jugador);
                if (prop != null) {
                    jugador.deshipotecarPropiedad(prop);
                }
            }else if (accionElecta == Acciones.Accion.COMPRAR) {
                Comprable comprable = obtenerComprable(jugador.getUbicacion());
                jugador.comprarComprable(comprable, jugador);
                //Propiedad prop = obtenerPropiedad(jugador.getUbicacion());
                //jugador.comprarPropiedad(prop,jugador);
            }
        }

    public boolean esPropiedad(int casillero) {
            if (casillero < tablero.getCantidadCasilleros()) {
                TipoCasillero tipoCasillero = tablero.getTipoCasillero(casillero);
                return tipoCasillero == TipoCasillero.PROPIEDAD;
            }
            return false;
        }

    public Propiedad obtenerPropiedadJugador(int casillero, Jugador jugador) {
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

    //faaaq
        public boolean esComprable(int casillero) {
            TipoCasillero tipoCasillero = tablero.getTipoCasillero(casillero);
            return tipoCasillero == TipoCasillero.ESTACION ||
                    tipoCasillero == TipoCasillero.PROPIEDAD;
        }

    public Comprable obtenerComprable(int casillero) {
            if(!esComprable(casillero)){
                System.out.println("Accion imposible de realizar");
                return null;
            }
            Comprable comprable;
            if(esPropiedad(casillero)){
                DePropiedad casilleroPropiedad = tablero.getPropiedad(casillero);
                comprable = casilleroPropiedad.getPropiedad();
                return comprable;
            }
            Estacion casilleroEstacion = tablero.getEstacion(casillero);
            comprable = casilleroEstacion.getEstacion();
            return comprable;
        }

    //ESTA FUNCION ESTA REPETIDA 3 VECES, DESPUES HAY QUE SOLUCIONARLO BY TOPG :)
    private Ansi obtenerColorANSI(Colores.Color color) {
        try {
            Field field = Ansi.Color.class.getDeclaredField(color.name());
            Ansi.Color ansiColor = (Ansi.Color) field.get(null);
            return Ansi.ansi().fg(ansiColor);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return Ansi.ansi();
        }
    }

}



/*
    public Propiedad obtenerPropiedad(int casillero) {
        if (esPropiedad(casillero)) {
            DePropiedad casilleroPropiedad = tablero.getPropiedad(casillero);
            return casilleroPropiedad.getPropiedad();
        }
        System.out.println("Accion imposible de realizar");
        return null;
    }
*/
//:



