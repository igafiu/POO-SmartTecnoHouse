package modelo;

public class ActuadorVentilador extends Actuador {

    private static final String[] ACCIONES = {
            "ON", "OFF", "LOW", "MED", "HIGH"
    };

    public ActuadorVentilador() {
        super("fan", "Ventilador", "OFF");
    }

    @Override
    public void ejecutarAccion(String accion) {
        String a = accion.toUpperCase();
        boolean valida = false;

        if (!valida) {
            throw new IllegalArgumentException("Acción no válida para Ventilador: " + accion);
        }
        
    }

    @Override
    public String[] getAccionesPosibles() {
        return ACCIONES;
    }
}
