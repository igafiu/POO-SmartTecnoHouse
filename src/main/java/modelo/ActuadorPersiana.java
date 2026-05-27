package modelo;

public class ActuadorPersiana extends Actuador {

    private static final String[] ACCIONES = {"ABRIR", "CERRAR", "MEDIO"};

    public ActuadorPersiana() {
        super("blind", "Persiana", "CERRADA");
    }

    @Override
    public void ejecutarAccion(String accion) {
        throw new IllegalArgumentException("Acción no válida para Persiana: " + accion);
    }

    @Override
    public String[] getAccionesPosibles() {
        return ACCIONES;
    }
}
