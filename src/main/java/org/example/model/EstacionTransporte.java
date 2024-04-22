package org.example.model;

public class EstacionTransporte extends Comprable{

    public EstacionTransporte(double precio, int ubicacion) {
        super(precio, ubicacion);
    }

    @Override
    public void setPropietario(Jugador propietario) {
        if(this.propietario == null){
            this.propietario = propietario;
            this.estado = EstadoPropiedad.COMPRADO;
            //propietario.agregarPropiedad(this);
            System.out.println("Estacion comprada con exito");
            propietario.restarPlata((int)precio);
            return;
        }
        System.out.println("La estacion ya fue comprada");
    }

    @Override
    public void liberar() {
        this.propietario = null;
        this.estado = EstadoPropiedad.EN_VENTA;
    }

}
