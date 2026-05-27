package modelo;

import java.util.List;

public class ReglaVentilacionConfortable implements Regla {

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

    }
}
