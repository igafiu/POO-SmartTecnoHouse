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
    }

    @Override
    public String[] getAccionesPosibles() {
        return ACCIONES;
    }
}
