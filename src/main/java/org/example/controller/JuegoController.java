package org.example.controller;

import org.example.funciones.FuncionColorPrints;
import org.example.funciones.FuncionesExtras;
import org.example.model.*;
import org.example.model.tipoCasilleros.*;
import org.fusesource.jansi.Ansi;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.TerminalBuilder;
import org.jline.terminal.Terminal;
import org.example.view.JuegoView;
import java.io.IOException;
import org.example.model.Accion;

import javax.security.auth.login.AccountException;
import java.util.List;

public class JuegoController {
    private final Juego juego;
    private JuegoView vistaJuego;
    private LineReader reader;
    private AdministradorDeMovimientos administradorDeMovimientos;
    private Tablero tablero;
    private ConstruccionController controllConstrucciones;
    private FachadaAcciones fachada;
    private TableroController controlTablero;
    private FuncionesExtras funcionesExtras;
    public JuegoController(Juego juego) throws IOException {
        System.out.println("Iniciando Juego");
        this.juego = juego;
        this.tablero = juego.getTablero();
        this.vistaJuego = new JuegoView(juego);
        this.controlTablero = new TableroController(tablero);
        this.administradorDeMovimientos = new AdministradorDeMovimientos(juego.getTablero());
        this.controllConstrucciones = new ConstruccionController(tablero.getBarrios());
        Terminal terminal = TerminalBuilder.terminal();
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
        this.fachada = new FachadaAcciones(new Hipotecar(),new Comprar(),new Vender(),new ConsultarPrecios(),new Construir(),new Deshipotecar(),new PagarFianza());
        this.funcionesExtras = new FuncionesExtras(juego);
    }
    public ConstruccionController getConstruccionController(){return this.controllConstrucciones;}



    public void jugarTurno() throws IOException {
        vistaJuego.muestaJugadoresInicial();
        juego.cambiarTurno();
        Jugador jugador = juego.getJugadorActual();
        if(jugador.getEstado() == Estado.EnJuego){
            jugarTurnoLibre(jugador);
        }
        else if(jugador.getEstado() == Estado.Preso){
            jugarTurnoPreso(jugador);
        }else{
            juego.eliminarJugador(jugador);
            juego.cambiarTurno();
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
                Accion accionElecta = acciones.getAccionPreso(numeroElecto);
                ejecutarAccion(accionElecta,jugador);
                numeroElecto = 0;
                jugador.quedaLibre();
                jugarTurnoLibre(jugador);
            }
            else{
                int dados = juego.tirarDados();
                Accion accionElecta = acciones.getAccionPreso(numeroElecto);
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



    public void jugarTurnoLibre(Jugador jugador) {
        int dados = juego.tirarDados();
        Ansi colorANSI = null;
        Ansi resetColor = null;
        FuncionColorPrints funcionColorPrints = new FuncionColorPrints();
        colorANSI = funcionColorPrints.obtenerColorANSI(jugador.getColor());
        resetColor = Ansi.ansi().reset();
        System.out.println(colorANSI + "Es el turno de " + jugador.getNombre() + "\n" + "Tus dados son: " + dados + "\n");
        int casillaAnterior = jugador.getUbicacion();
        int casillaActual = administradorDeMovimientos.avanzarJugador(jugador, dados);
        System.out.println("Usted esta en el casillero: " + casillaActual + "\n" + resetColor);
        pagarBono(jugador, dados, casillaAnterior);
        Casillero casillero = tablero.getCasillero(casillaActual);

        if (casillero instanceof CasilleroEjecutable) {
            ejecutar(jugador, casillaActual);
        }

        if (jugador.getEstado() == Estado.Preso) return;

        int numeroElecto = 1;

        vistaJuego.mostrar();
        Acciones acciones = new Acciones();
        acciones.getAcciones(colorANSI, resetColor);
        while (numeroElecto != 0) {
            String accion = reader.readLine(colorANSI + "Seleccione la accion que quiere realizar indicando su numero (NUMERO):\n" + resetColor);
            numeroElecto = corroboroAccion(accion);
            if (numeroElecto == Constantes.NEGATIVO) {
                System.out.println("Accion inexistente\n");
            } else {
                Accion accionElecta = acciones.getAccion(numeroElecto);
                if (accionElecta == null) {
                    System.out.println("Accion inexistente");
                }else{
                ejecutarAccion(accionElecta, jugador);
            }
            }
        }
        if (jugador.estaEnDeuda()) {
            int ubicacion = jugador.getUbicacion();
            if (tablero.getCasillero(ubicacion).getTipo() == TipoCasillero.MULTA){
                checkDeudaMulta(jugador);
            }else {
                Propiedad propiedad = tablero.getPropiedad(ubicacion).getPropiedad();
                checkDeudaComprable(jugador, propiedad);
            }
        }

        if (jugador.estaEnQuiebra()) {
            System.out.printf("%s perdio!\n", jugador.getNombre());
            controlTablero.eliminarPropiedadesDelJugadorEnQuiebra(jugador);
            juego.eliminarJugador(jugador);
            juego.terminado();
        }

    }
        public void checkDeudaMulta(Jugador jugador) {
            Casillero casilleroDeMulta = tablero.getCasillero(jugador.getUbicacion());

            if(jugador.restarPlata(casilleroDeMulta.getPrecio()) ){
                System.out.println("Perfecto! El jugador "+jugador.getNombre() +" pudo pagar su multa!");
                jugador.setEstado(Estado.EnJuego);
            }else{
                System.out.println("EL JUGADOR "+ jugador.getNombre()+" NO PAGO SU MULTA! ENTRÓ EN BANCARROTA");
                jugador.setQuiebra();
            }
        }
        public void checkDeudaComprable(Jugador jugador,Propiedad propiedad){
            if (jugador.restarPlata(propiedad.getAlquiler())){
                System.out.println("Perfecto! El jugador "+jugador.getNombre() +" pudo pagar su deuda!");
                jugador.setEstado(Estado.EnJuego);
            }else{
                System.out.println("EL JUGADOR "+ jugador.getNombre()+" NO PAGO SU DEUDA! ENTRÓ EN BANCARROTA");
                jugador.setQuiebra();
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

    public void ejecutarAccion(Accion accionElecta, Jugador jugador) {
            if (accionElecta == Accion.PAGAR_FIANZA) {
                fachada.pagar_fianza(jugador, (int) juego.getFianza(), tablero, controllConstrucciones);
            }else if(accionElecta == Accion.COMPRAR){
                fachada.comprar(jugador,0,tablero,controllConstrucciones);
            }else if (accionElecta != Accion.TERMINAR_TURNO){
                CheckStrToInt checkStrToInt = new CheckStrToInt();
                String casillero = reader.readLine("Seleccione el casillero en que se encuentra la propiedad (NUMERO):");
                int numero = (checkStrToInt.checkStringToInt(casillero));
                switch (accionElecta) {
                    case CONSTRUIR -> fachada.construir(jugador, numero, tablero, controllConstrucciones);
                    case VENDER -> fachada.vender(jugador, numero, tablero, controllConstrucciones);
                    case HIPOTECAR -> fachada.hipotecar(jugador, numero, tablero, controllConstrucciones);
                    case DESHIPOTECAR -> fachada.deshipotecar(jugador, numero, tablero, controllConstrucciones);
                    case CONSULTAR_PRECIO_CASA -> fachada.consultar_precio_casa(jugador, numero, tablero, controllConstrucciones);
                }
            }
    }

            public void pagarBono(Jugador jugador,int dados,int casillaAnterior){
            if((casillaAnterior+dados) >= tablero.getCantidadCasilleros()) {
                juego.pagarBono(jugador);
            }
        }



}





