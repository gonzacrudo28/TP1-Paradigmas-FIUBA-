package org.example.model;

public class EstacionTransporte extends Comprable{

    public EstacionTransporte(double precio, int ubicacion) {
        super(precio, ubicacion);
        this.propietario = null;
    }

    @Override
    public void setPropietario(Jugador propietario) {
        if(this.propietario == null){
            this.propietario = propietario;
            this.estado = EstadoPropiedades.COMPRADO;
            System.out.println("Estacion comprada con exito");
            propietario.restarPlata(precio);
            propietario.agregarEstacion(this);
            return;
        }
        if (propietario!= null){
            System.out.println("La estacion ya fue comprada");
        }
    }

    public void venderComprable(){
        propietario.sumarPlata(precio);
        propietario.eliminarComprable(this);
        setPropietario(null);
        this.estado = EstadoPropiedades.EN_VENTA;
    }

    public String getNombrePropietario() {
        return propietario.getNombre();
    }
}
