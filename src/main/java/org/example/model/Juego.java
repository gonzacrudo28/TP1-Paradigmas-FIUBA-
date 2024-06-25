package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.controller.*;
import org.example.funciones.FuncionColorPrints;
import org.example.funciones.FuncionesExtras;
import org.example.model.tipoCasilleros.Casillero;
import org.example.model.tipoCasilleros.CasilleroEjecutable;
import org.example.model.tipoCasilleros.DePaso;
import org.example.model.tipoCasilleros.TipoCasillero;
import org.fusesource.jansi.Ansi;

public class Juego {
    private ArrayList<Jugador> jugadores;
    private Tablero tablero;
    private AdministradorTurnos administradorDeTurnos;
    private AdministradorDeMovimientos administradorDeMovimientos;
    private Configuracion configuracion;
    private Banco banco;
    private Suscriptor suscriptor;
    private FachadaAcciones fachada;
    private FuncionesExtras funcionesExtras;
    private ConstruccionController controllConstrucciones;

    public Juego(List<String> configuraciones){
        Configuracion configuracion = new Configuracion(configuraciones);
        this.configuracion = configuracion;
        jugadores = configuracion.getJugadores();
        this.administradorDeTurnos = new AdministradorTurnos(jugadores);
        this.tablero = new Tablero(configuracion);
        this.administradorDeMovimientos = new AdministradorDeMovimientos(this.tablero);
        this.banco = new Banco(configuracion.getMontoVuelta());
        this.funcionesExtras = new FuncionesExtras(tablero);
        this.controllConstrucciones = new ConstruccionController(tablero.getBarrios());
        this.fachada = new FachadaAcciones(new Hipotecar(funcionesExtras),new Comprar(funcionesExtras),new Vender(funcionesExtras),new ConsultarPrecios(funcionesExtras),new Construir(funcionesExtras),new Deshipotecar(funcionesExtras),new PagarFianza());
    }

    public void addObserver(Suscriptor suscriptor){
        this.suscriptor = suscriptor;
    }

    public Jugador getJugadorActual() {
       return administradorDeTurnos.getTurnoActual();
    }

    public Boolean terminado() {
        return checkEstadoJugadores(jugadores);
    }

    public void eliminarJugador(Jugador jugador){
        jugadores.remove(jugador);
    }

    public void cambiarTurno() {
        administradorDeTurnos.avanzarTurno();
    }

    public int tirarDados(){
        return administradorDeTurnos.tirarDados(configuracion.getCantidadDeLadosEnDado());
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public ArrayList<Jugador> getJugadores() {
        return this.jugadores;
    }

    public String pagarBono(Jugador jugador){
        return banco.pagarBono(jugador);
    }

    public boolean checkEstadoJugadores(List<Jugador> jugadores){
        for(Jugador jugador: jugadores){
            if (jugador.getEstado().equals(Estado.Gano)){
                return true;
            }
        }
        return jugadores.size() == 1;
    }
/* --------------------------------primeros cambios en juego----------------------------------------------------*/
    public void empezarTurno(Jugador jugador,Ansi colorANSI){

        suscriptor.recibirNoticia(jugador.obtenerAccionesDisponibles(colorANSI));
    }
    public void realizarJuego(Jugador jugador,Ansi colorANSI){
        if (jugador.getEstado().equals(Estado.Preso)){
            suscriptor.recibirNoticia(jugarTurnoPreso(jugador));
            //suscriptor.recibirNoticia(jugador.obtenerAccionesDisponibles(colorANSI));
        }
        else{
            jugarTurnoLibre(jugador);
            //suscriptor.recibirNoticia(jugador.obtenerAccionesDisponibles(colorANSI));
        }
    }
/*-------------------------------------- Juego controller  ------------------------------------------*/
/*    private void jugarTurnoPreso(Jugador jugador){
        Ansi colorANSI = null;
        Ansi resetColor = Ansi.ansi().reset();
        FuncionColorPrints funcionColorPrints = new FuncionColorPrints();
        colorANSI = funcionColorPrints.obtenerColorANSI(jugador.getColor());
        suscriptor.recibirNoticia(colorANSI+"Es el turno de " + jugador.getNombre() + "\n");
        //System.out.println(colorANSI+"Es el turno de " + jugador.getNombre() + "\n");

        Acciones acciones = new Acciones();
        acciones.accionesJugadorPreso(colorANSI,resetColor);
        Scanner scanner = new Scanner(System.in);
        int numeroElecto = -1;
        while((numeroElecto != 0) && (numeroElecto != 1) && (jugador.getCondena() != 0)){
            suscriptor.recibirNoticia("Seleccione la accion que quiere realizar indicando su numero (NUMERO):\n");
            //System.out.println("Seleccione la accion que quiere realizar indicando su numero (NUMERO):\n");
            String accion = scanner.nextLine();
            numeroElecto = corroboroAccion(accion);
        }
        if (numeroElecto == -1){
            FuncionesExtras.delay(1000);
            suscriptor.recibirNoticia("El jugador "+ jugador.getNombre() + " complió su condena!");
            //System.out.println();
            FuncionesExtras.delay(1000);
            jugador.setEstado(Estado.EnJuego);

        } else if(numeroElecto == 1){
            Accion accionElecta = acciones.getAccionPreso(numeroElecto);
            ejecutarAccion(accionElecta,jugador);
        }else{
            int dados = tirarDados();
            FuncionesExtras.delay(1000);
            suscriptor.recibirNoticia("Saco: " + dados);
            //System.out.println("Saco: " + dados);
            FuncionesExtras.delay(1500);
            if(dados > jugador.getCondena()){
                jugador.quedaLibre();
                suscriptor.recibirNoticia(jugador.getNombre()+ " queda libre por sacar "+ dados+  " (dados) mayor que el numero de condena ("+ jugador.getCondena()+")");
                //System.out.println(jugador.getNombre()+ " queda libre por sacar "+ dados+  " (dados) mayor que el numero de condena ("+ jugador.getCondena()+")");
            } else {
                jugador.restarCondena();
                suscriptor.recibirNoticia(jugador.getNombre()+ " sigue preso. Ahora su condena es de ("+ jugador.getCondena()+")");
                //System.out.println(jugador.getNombre()+ " sigue preso. Ahora su condena es de ("+ jugador.getCondena()+")");
            }
            FuncionesExtras.delay(1000);
        }
        if(jugador.getEstado() == Estado.EnJuego){
            jugarTurnoLibre(jugador);
        }
    }*/
private String jugarTurnoPreso(Jugador jugador){
    Scanner scanner = new Scanner(System.in);
    int numeroElecto = -1;
    while ((numeroElecto != 0) && (numeroElecto != 1) && (jugador.getCondena() != 0)) {
        String accion = scanner.nextLine();
        numeroElecto = corroboroAccion(accion);
    }
    if (numeroElecto == -1) {
        jugador.setEstado(Estado.EnJuego);
        jugador.actualizarEstadoAcciones();
        return ("El jugador " + jugador.getNombre() + " complió su condena!");

    }else if(numeroElecto == 1) {
        Accion accionElecta = new Acciones().getAccion(numeroElecto,EstadoAcciones.PRESO);
        //return ejecutarAccion(accionElecta, jugador);
        suscriptor.recibirNoticia(ejecutarAccion(accionElecta,jugador));

    }else{
        int dados = tirarDados();
        FuncionesExtras.delay(1000);
        String mensaje = ("Saco: " + dados + "\n") ;
        if (dados > jugador.getCondena()) {
            jugador.quedaLibre();
            mensaje += (jugador.getNombre() + " queda libre por sacar " + dados + " (dados) mayor que el numero de condena (" + jugador.getCondena() + ")");
            return mensaje;
        }else {
            jugador.restarCondena();
            mensaje += (jugador.getNombre() + " sigue preso. Ahora su condena es de (" + jugador.getCondena() + ")");
            return mensaje;
        }
    }
    return "print de preso";
}


    private void jugarTurnoLibre(Jugador jugador) {
        int dados = tirarDados();
        Ansi colorANSI = null;
        Ansi resetColor = Ansi.ansi().reset();
        FuncionColorPrints funcionColorPrints = new FuncionColorPrints();
        colorANSI = funcionColorPrints.obtenerColorANSI(jugador.getColor());
        suscriptor.recibirNoticia(colorANSI + "Es el turno de " + jugador.getNombre() + "\n" + "Te toco el numero: " + dados + "\n");
        //System.out.println(colorANSI + "Es el turno de " + jugador.getNombre() + "\n" + "Te toco el numero: " + dados + "\n");
        int casillaAnterior = jugador.getUbicacion();
        int casillaActual = administradorDeMovimientos.avanzarJugador(jugador, dados);
        //System.out.println("Usted esta en el casillero: " + casillaActual + "\n" + resetColor);
        suscriptor.recibirNoticia("Usted esta en el casillero: " + casillaActual + "\n" + resetColor);
        suscriptor.recibirNoticia(pagarBono(jugador, dados, casillaAnterior));
        Casillero casillero = tablero.getCasillero(casillaActual);

        if (casillero.getEsEjecutable()) {
            suscriptor.recibirNoticia(ejecutar(jugador, casillaActual));
        }
        if (jugador.getEstado() == Estado.Preso) return;
        int numeroElecto = 1;
        suscriptor.recibirEstadoJugadores();
        suscriptor.recibirNoticia(jugador.obtenerAccionesDisponibles(colorANSI));
        //vistaJuego.mostrar();
        Acciones acciones = new Acciones();
        acciones.acciones(colorANSI, resetColor);
        Scanner scanner = new Scanner(System.in);
        while (numeroElecto != 0) {
            suscriptor.recibirNoticia(colorANSI + "Seleccione la accion que quiere realizar indicando su numero (NUMERO):" + resetColor);
            //System.out.println(colorANSI + "Seleccione la accion que quiere realizar indicando su numero (NUMERO):\n"+resetColor);
            String accion = scanner.nextLine();
            numeroElecto = corroboroAccion(accion);
            if (numeroElecto == Constantes.NEGATIVO) {
                suscriptor.recibirNoticia("Accion inexistente\n");
                //System.out.println("Accion inexistente\n");
            } else {
                Accion accionElecta = acciones.getAccion(numeroElecto, jugador.getEstadoAcciones());
                if (accionElecta == null || accionElecta == Accion.PAGAR_FIANZA || accionElecta == Accion.TIRAR_DADOS) {
                    suscriptor.recibirNoticia("Accion inexistente\n");
                    //System.out.println("Accion inexistente");
                }else{
                    suscriptor.recibirNoticia(ejecutarAccion(accionElecta, jugador));
                }
            }
        }
        if (jugador.estaEnDeuda()) {
            int ubicacion = jugador.getUbicacion();
            if (tablero.getCasillero(ubicacion).getTipo() == TipoCasillero.MULTA){
                suscriptor.recibirNoticia(checkDeuda(jugador,tablero.getCasillero(ubicacion).getPrecio() , "multa"));
            }else {
                Propiedad propiedad = tablero.getPropiedad(ubicacion).getPropiedad();
                suscriptor.recibirNoticia(checkDeuda(jugador, propiedad.getAlquiler(), "alquiler"));
            }
        }
        if (jugador.estaEnQuiebra()) {
            suscriptor.recibirNoticia(jugador.getNombre() + " perdio!\n");
            //System.out.printf("%s perdio!\n", jugador.getNombre());
            eliminarPropiedadesDelJugadorEnQuiebra(jugador);
            eliminarJugador(jugador);
            terminado();
        }
        if (ComprobarGanarJugador(jugador)){
            terminado();
        }
    }

    private String ejecutarAccion(Accion accionElecta, Jugador jugador) {
        if (accionElecta == Accion.COMPRAR){
            return fachada.comprar(jugador,0,controllConstrucciones);
        }else if (accionElecta != Accion.TERMINAR_TURNO && accionElecta != Accion.PAGAR_FIANZA){
            CheckStrToInt checkStrToInt = new CheckStrToInt();
            Scanner scanner = new Scanner(System.in);
            //System.out.println("Seleccione el casillero en que se encuentra la propiedad (NUMERO):");
            suscriptor.recibirNoticia("Seleccione el casillero en que se encuentra la propiedad (NUMERO):");
            String casillero = scanner.nextLine();
            int numero = (checkStrToInt.checkStringToInt(casillero));
            switch (accionElecta) {
                case CONSTRUIR -> {
                    return(fachada.construir(jugador, numero, controllConstrucciones));
                }
                case VENDER -> {
                    return fachada.vender(jugador, numero, controllConstrucciones);
                }
                case HIPOTECAR -> {
                    return fachada.hipotecar(jugador, numero, controllConstrucciones);
                }
                case DESHIPOTECAR -> {
                    return fachada.deshipotecar(jugador, numero, controllConstrucciones);
                }
                case CONSULTAR_PRECIO_CASA -> {
                    return fachada.consultar_precio_casa(jugador, numero, controllConstrucciones);
                }
            }
        }
        if (accionElecta == Accion.PAGAR_FIANZA){
            return fachada.pagar_fianza(jugador, tablero.getCarcel());
        }
        return "";
    }

    private String pagarBono(Jugador jugador,int dados,int casillaAnterior){
        if((casillaAnterior+dados) >= tablero.getCantidadCasilleros()) {
            return pagarBono(jugador);
        }else{
            return "";
        }
    }

/*    private String checkDeudaMulta(Jugador jugador) {
        Casillero casilleroDeMulta = tablero.getCasillero(jugador.getUbicacion());
        if(jugador.restarPlata(casilleroDeMulta.getPrecio()) ){
            //System.out.println("Perfecto! El jugador "+jugador.getNombre() +" pudo pagar su multa!");
            jugador.setEstado(Estado.EnJuego);
            return "Perfecto! El jugador "+jugador.getNombre() +" pudo pagar su multa!";
        }else{
            //System.out.println("EL JUGADOR "+ jugador.getNombre()+" NO PAGO SU MULTA! ENTRÓ EN BANCARROTA");
            jugador.setQuiebra();
            return "EL JUGADOR "+ jugador.getNombre()+" NO PAGO SU MULTA! ENTRÓ EN BANCARROTA";
        }
    }

    private String checkDeudaComprable(Jugador jugador,Propiedad propiedad){
        if (jugador.restarPlata(propiedad.getAlquiler())){
            //System.out.println("Perfecto! El jugador "+jugador.getNombre() +" pudo pagar su deuda!");
            jugador.setEstado(Estado.EnJuego);
            return "Perfecto! El jugador "+jugador.getNombre() +" pudo pagar su deuda!";
        }else{
            //System.out.println("EL JUGADOR "+ jugador.getNombre()+" NO PAGO SU DEUDA! ENTRÓ EN BANCARROTA");
            jugador.setQuiebra();
            return "EL JUGADOR "+ jugador.getNombre()+" NO PAGO SU DEUDA! ENTRÓ EN BANCARROTA";
        }
    }*/
private String checkDeuda(Jugador jugador, double montoDeuda, String tipoDeuda) {
    if (jugador.restarPlata(montoDeuda)) {
        jugador.setEstado(Estado.EnJuego);
        return "Perfecto! El jugador " + jugador.getNombre() + " pudo pagar su " + tipoDeuda + "!";
    } else {
        jugador.setQuiebra();
        return "EL JUGADOR " + jugador.getNombre() + " NO PAGO SU " + tipoDeuda + "! ENTRÓ EN BANCARROTA";
    }
}

    private String ejecutar(Jugador jugador, int ubicacionJugador){
        CasilleroEjecutable casillero = tablero.getCasilleroEjecutable(ubicacionJugador);
        return casillero.ejecutarCasillero(jugador);
    }

    private int corroboroAccion(String accion) {
        CheckStrToInt checkStrToInt = new CheckStrToInt();
        return checkStrToInt.checkStringToInt(accion);
    }
    public void eliminarPropiedadesDelJugadorEnQuiebra(Jugador jugador){
        ArrayList<Propiedad> propiedades = jugador.getPropiedades();
        for (Propiedad propiedad : propiedades) {
            propiedad.setPropietario(null);
            int ubicacion = propiedad.getUbicacion();
            DePaso NuevoCasilleroDePaso = new DePaso(ubicacion);
            tablero.getTodosLosCasilleros()[ubicacion] =   NuevoCasilleroDePaso;
        }}

    public boolean ComprobarGanarJugador(Jugador jugador) {
        ArrayList<Barrio> barrios = tablero.getBarrios();
        ArrayList<Propiedad> propiedadesJugador = jugador.getPropiedades();
        for (Barrio barrio : barrios) {
            ArrayList<Propiedad> propiedadesBarrio = barrio.getPropiedades();
            boolean todasPropiedadesPertenecenAJugador = propiedadesJugador.containsAll(propiedadesBarrio);
            if (todasPropiedadesPertenecenAJugador) {
                boolean todasPropiedadesEnHotel = true;
                for (Propiedad propiedad : propiedadesBarrio) {
                    if (propiedad.getConstrucciones() != Construcciones.HOTEL) {
                        todasPropiedadesEnHotel = false;
                        break;
                    }
                }
                if (todasPropiedadesEnHotel) {
                    jugador.setEstado(Estado.Gano);
                    return true;
                }
            }
        }
        return false;
    }
}
