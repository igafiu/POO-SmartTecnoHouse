package modelo;

import java.util.List;

public class ReglaIluminacionAutomatica extends Regla {

    private boolean activa = true;

    @Override
    public String getID() {
        return "R2";
    }

    @Override
    public String getDescripcion() {
        return "Iluminación automática (Luz < 300 lux y Presencia)";
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
        Sensor pir  = buscarSensor(sensores, "PIR-001");
        Actuador bombilla = buscarActuador(actuadores, "bulb");

        if (luz == null || pir == null || bombilla == null) return;

        if (luz.getValor() < 300 && pir.getValor() == 1) {
            bombilla.ejecutarAccion("ON");
        } else {
            bombilla.ejecutarAccion("OFF");
        }
    }
}
