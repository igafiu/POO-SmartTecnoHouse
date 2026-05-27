package modelo;

import java.util.List;

public class ReglaAhorroNocturno extends Regla {

    private boolean activa = true;

    @Override
    public String getID() {
        return "R3";
    }

    @Override
    public String getDescripcion() {
        return "Ahorro nocturno (Luz > 700 lux y 20 ≤ Temp ≤ 25)";
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
        Sensor luz  = buscarSensor(sensores, "LGH-001");
        Sensor temp = buscarSensor(sensores, "TMP-001");
        Sensor pir  = buscarSensor(sensores, "PIR-001");
        Actuador fan      = buscarActuador(actuadores, "fan");
        Actuador bombilla = buscarActuador(actuadores, "bulb");

        if (luz == null || temp == null || pir == null) return;

        double t = temp.getValor();
        boolean condicion = (luz.getValor() > 700 && t >= 20 && t <= 25) || pir.getValor() == 0;

        if (condicion) {
            if (fan != null)      fan.ejecutarAccion("OFF");
            if (bombilla != null) bombilla.ejecutarAccion("OFF");
        }
    }
}
