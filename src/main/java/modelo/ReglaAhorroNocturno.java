package modelo;

import java.util.List;

public class ReglaAhorroNocturno implements Regla {

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

    }
}
