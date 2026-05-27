package modelo;

public class ActuadorVentilador extends Actuador {

    private static final String[] ACCIONES = {
            "ON", "OFF", "LOW", "MED", "HIGH"
    };

    public ActuadorVentilador() {
        super("fan", "Ventilador", "OFF");
    }

    @Override
    protected void procesarAccion(String accion) {
        String a = accion.toUpperCase();
        boolean valida = false;
        for (String opcion : ACCIONES) {
            if (opcion.equals(a)) {
                valida = true;
                break;
            }
        }
        if (!valida) {
            throw new IllegalArgumentException("Acción no válida para Ventilador: " + accion);
        }
        estadoActual = a.equals("ON") ? "LOW" : a;
    }

    @Override
    public String[] getAccionesPosibles() {
        return ACCIONES;
    }
}
