package modelo;

import java.util.List;

public class ReglaVentilacionConfortable extends Regla {

    private boolean activa = true;

    @Override
    public String getID() {
        return "R1";
    }

    @Override
    public String getDescripcion() {
        return "Ventilación confortable (Temp > 28 y Presencia)";
    }

    @Override
    public boolean isActiva() {
        return activa;
    }

    @Override
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        Sensor temp = buscarSensor(sensores, "TMP-001");
        Sensor pir  = buscarSensor(sensores, "PIR-001");
        Actuador fan = buscarActuador(actuadores, "fan");

        if (temp == null || pir == null || fan == null) return;

        if (temp.getValor() > 28 && pir.getValor() == 1) {
            fan.ejecutarAccion("HIGH");
        } else {
            fan.ejecutarAccion("OFF");
        }
    }
}
