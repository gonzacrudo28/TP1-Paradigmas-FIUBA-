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
            propietario.restarPlata((int)precio);
            propietario.agregarEstacion(this);
            return;
        }
        System.out.println("La estacion ya fue comprada");
    }

    public void venderEstacion(){
        propietario.sumarPlata(getPrecio());
        propietario.eliminarEstacion(this);
        setPropietario(null);
        this.estado = EstadoPropiedades.EN_VENTA;
    }

    public String getNombrePropietario() {
        return propietario.getNombre();
    }
}
