package org.example.model.tipoCasilleros;
public class Carcel extends Casillero{
    public int fianza;
    public int cantTurnos;
    public Carcel(int ubic,int turnos,int pago){
        this.ubicacion = ubic;
        this.tipo = TipoPropiedad.Carcel;
        cantTurnos = turnos;
        fianza = pago;
    }

}
