package modelo;

public class ActuadorPersiana extends Actuador {

    private static final String[] ACCIONES = {"ABRIR", "CERRAR", "MEDIO"};

    public ActuadorPersiana() {
        super("blind", "Persiana", "CERRADA");
    }

    @Override
    protected void procesarAccion(String accion) {
        String a = accion.toUpperCase();
        for (String opcion : ACCIONES) {
            if (opcion.equals(a)) {
                switch (a) {
                    case "ABRIR"  -> estadoActual = "ABIERTA";
                    case "CERRAR" -> estadoActual = "CERRADA";
                    default       -> estadoActual = "MEDIO";
                }
                return;
            }
        }
        throw new IllegalArgumentException("Acción no válida para Persiana: " + accion);
    }

    @Override
    public String[] getAccionesPosibles() {
        return ACCIONES;
    }
}
