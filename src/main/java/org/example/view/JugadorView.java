package org.example.view;



import org.example.model.Colores;
import org.example.model.Jugador;
import java.util.List;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import java.lang.reflect.Field;

public class JugadorView {
    private final List<Jugador> jugadores;

    public JugadorView(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void mostrarJugadores() {
        for (Jugador jugador : jugadores) {
        Ansi colorANSI = obtenerColorANSI(jugador.getColor());
        Ansi resetColor = Ansi.ansi().reset();

        System.out.println("=======================================");
        System.out.println("Nombre: " + colorANSI + jugador.getNombre() + resetColor);
        System.out.println("Color: " + colorANSI + jugador.getColor().toString() + resetColor);
        System.out.println("Plata: " + colorANSI + jugador.getPlata() + resetColor);
        System.out.println("Ubicación: " + colorANSI + jugador.getUbicacion() + resetColor);
        System.out.println("Estado: " + colorANSI + jugador.getEstado().toString() + resetColor);
        System.out.println("Condena: " + colorANSI + jugador.getCondena() + resetColor);
        System.out.println("=======================================");
        }
    }

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
