package org.example.model.tipoCasilleros;
public class Carcel extends Casillero{
    public int fianza;
    public int cantTurnos;
    public Carcel() {
        this.efecto = "CARCEL";
        this.tipo=TipoCasillero.CARCEL;
    }

}
/**
 * public Carcel(int ubic,int turnos,int pago){
 *         this.ubicacion = ubic;
 *         this.tipo = TipoPropiedad.Carcel;
 *         cantTurnos = turnos;
 *         fianza = pago;
 *     }
 */