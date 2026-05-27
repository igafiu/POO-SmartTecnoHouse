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

    protected abstract void procesarAccion(String accion);

    public abstract String[] getAccionesPosibles();

    // Llamado por las reglas automaticas
    public void ejecutarAccion(String accion) {
        procesarAccion(accion);
        GestorLogs.getInstance().addLog(id, estadoActual, "REGLA");
    }

    // Llamado por el controlador cuando la accion es manual
    public void ejecutarAccion(String accion, String fuente) {
        procesarAccion(accion);
        GestorLogs.getInstance().addLog(id, estadoActual, fuente);
    }

    // Solo para restaurar estado desde persistencia, sin generar log
    public void setEstado(String estado) {
        this.estadoActual = estado;
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
        return estadoActual;
    }
}
