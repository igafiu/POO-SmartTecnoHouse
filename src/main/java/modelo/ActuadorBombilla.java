package modelo;

public class ActuadorBombilla extends Actuador {

    private static final String[] ACCIONES = {"ON", "OFF"};

    public ActuadorBombilla() {
        super("bulb", "Bombilla", "OFF");
    }

    @Override
    protected void procesarAccion(String accion) {
        String a = accion.toUpperCase();
        for (String opcion : ACCIONES) {
            if (opcion.equals(a)) {
                estadoActual = a;
                return;
            }
        }
        throw new IllegalArgumentException("Acción no válida para Bombilla: " + accion);
    }

    @Override
    public String[] getAccionesPosibles() {
        return ACCIONES;
    }
}
