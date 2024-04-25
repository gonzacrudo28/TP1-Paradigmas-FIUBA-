package org.example.controller;

import org.example.funciones.FuncionColorPrints;
import org.example.model.*;
import org.example.model.tipoCasilleros.*;
import org.fusesource.jansi.Ansi;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.TerminalBuilder;
import org.jline.terminal.Terminal;
import org.example.view.JuegoView;
import java.io.IOException;
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



    public void jugarTurno() throws IOException {
        vistaJuego.muestaJugadoresInicial();
        juego.cambiarTurno();
        Jugador jugador = juego.getJugadorActual();
        if(jugador.getEstado() == Estado.EnJuego){
            jugarTurnoLibre(jugador);
        }
        else if(jugador.getEstado() == Estado.Preso){
            jugarTurnoPreso(jugador);

        }
    }

    public void jugarTurnoPreso(Jugador jugador){
        Ansi colorANSI = null;
        FuncionColorPrints funcionColorPrints = new FuncionColorPrints();
        colorANSI = funcionColorPrints.obtenerColorANSI(jugador.getColor());
        System.out.println(colorANSI+"Es el turno de " + jugador.getNombre() + "\n");
        Acciones acciones = new Acciones();
        acciones.getAccionesJugadorPreso();
        System.out.println(jugador.getNombre()+" esta preso, para pagar la fianza selecciona el 1 :) /n" +
               " o 0 si queres tirar dados para probar suerte");

        int numeroElecto = 1;
        while (numeroElecto != 0){
            String accion = reader.readLine("Seleccione la accion que quiere realizar indicando su numero (NUMERO):\n");
            numeroElecto = corroboroAccion(accion);
            if ( (numeroElecto != 0) && (numeroElecto != 1) ){
                System.out.println("Accion inexistente\n");
            }

            else if(numeroElecto == 1) {
                Acciones.Accion accionElecta = acciones.getAccionPreso(numeroElecto);
                ejecutarAccion(accionElecta,jugador);
                numeroElecto = 0;
                jugador.quedaLibre();
                jugarTurnoLibre(jugador);
            }
            else{
                int dados = juego.tirarDados();
                Acciones.Accion accionElecta = acciones.getAccionPreso(numeroElecto);
                ejecutarAccion(accionElecta,jugador);
                if(dados > jugador.getCondena()){
                    jugador.quedaLibre();
                    System.out.println(jugador.getNombre()+ "queda libre por sacar "+ dados+
                            "(dados) mayor que el numero de condena");
                }
                else {
                    jugador.restarCondena();
                }
            }
        }
    }


    public void jugarTurnoLibre(Jugador jugador){
        int dados = juego.tirarDados();
        Ansi colorANSI = null;
        Ansi resetColor = null;
        FuncionColorPrints funcionColorPrints = new FuncionColorPrints();
        colorANSI = funcionColorPrints.obtenerColorANSI(jugador.getColor());
        resetColor = Ansi.ansi().reset();
        System.out.println(colorANSI+"Es el turno de " + jugador.getNombre() + "\n" + "Tus dados son: " + dados + "\n");
        int casillaAnterior = jugador.getUbicacion();
        int casillaActual = administradorDeMovimientos.avanzarJugador(jugador, dados);
        System.out.println("Usted esta en el casillero: " + casillaActual + "\n" + resetColor);
        pagarBono(jugador,dados,casillaAnterior);
        Casillero casillero = tablero.getCasillero(casillaActual);

        if (casillero instanceof CasilleroEjecutable) {
            ejecutar(jugador,casillaActual);
        }

        if(jugador.getEstado() == Estado.Preso) return;

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

                        jugador.hipotecarPropiedad(tablero.getBarrio(prop),prop);

                }
            } else if (accionElecta == Acciones.Accion.PAGAR_FIANZA) {
                try {
                    jugador.pagarFianza(juego.getFianza());
                } catch (Exception e){
                    //Mostrar las acciones de hipotecar
                }

            } else if (accionElecta == Acciones.Accion.DESHIPOTECAR) {
                String casillero = reader.readLine("Seleccione el casillero en que se encuentra la porpiedad(NUMERO):");
                int propiedad = (checkStrToInt.checkStringToInt(casillero));
                Propiedad prop = obtenerPropiedadJugador(propiedad,jugador);
                if (prop != null) {
                    jugador.deshipotecarPropiedad(prop);
                }
            }else if (accionElecta == Acciones.Accion.COMPRAR) {
                int ubicacionJugador = jugador.getUbicacion();
                if (esComprable(ubicacionJugador)) {
                    try {
                        Comprable comprable = obtenerComprable(ubicacionJugador);
                        jugador.comprarComprable(comprable, jugador);
                    }catch (Exception e){
                        //Mostrar hipotecar
                    }

                }
            }else if (accionElecta == Acciones.Accion.CONSULTAR_PRECIO_CASA){
                String casillero = reader.readLine("Seleccione el casillero en que se encuentra la porpiedad(NUMERO):");
                int casilleroPropiedad = (checkStrToInt.checkStringToInt(casillero));
                Propiedad propiedad = obtenerPropiedad(casilleroPropiedad);
                System.out.println("El precio de una casa en esa propiedad es " + propiedad.getPrecioCasa());
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


        public boolean esComprable(int casillero) {
            TipoCasillero tipoCasillero = tablero.getTipoCasillero(casillero);
            return tipoCasillero == TipoCasillero.ESTACION ||
                    tipoCasillero == TipoCasillero.PROPIEDAD;
        }

        public void pagarBono(Jugador jugador,int dados,int casillaAnterior){
            if((casillaAnterior+dados) >= tablero.getCantidadCasilleros()) {
                juego.pagarBono(jugador);
            }
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

    public Propiedad obtenerPropiedad(int casillero) {
        if (esPropiedad(casillero)) {
            DePropiedad casilleroPropiedad = tablero.getPropiedad(casillero);
            return casilleroPropiedad.getPropiedad();
        }
        System.out.println("Accion imposible de realizar");
        return null;
    }


}





