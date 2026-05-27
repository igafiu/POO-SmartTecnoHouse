package modelo;

public abstract class Actuador implements IDispositivo {

    protected String id;
    protected String nombre;
    protected String estadoActual;

    protected Actuador(String id, String nombre, String estadoInicial) {
        this.id = id;
        this.nombre = nombre;
        this.estadoActual = estadoInicial;
    }

    public abstract void ejecutarAccion(String accion);

    public abstract String[] getAccionesPosibles();

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
        return estadoActual;
    }
}
