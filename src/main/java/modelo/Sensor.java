package modelo;

import java.util.Random;

public abstract class Sensor implements IDispositivo {

    protected String id;
    protected String nombre;
    protected double valorActual;
    protected String unidad;

    protected static final Random random = new Random();

    protected Sensor(String id, String nombre, String unidad) {
        this.id = id;
        this.nombre = nombre;
        this.valorActual = 0;
        this.unidad = unidad;
    }

    public abstract void actualizarValor();

    public double getValor() {
        return valorActual;
    }

    public void setValor(double valor) {
        this.valorActual = valor;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getEstadoActual() {
        return valorActual + " " + unidad;
    }
}
