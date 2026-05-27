package modelo;

import java.util.List;

public class ReglaIluminacionAutomatica implements Regla {

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

    }
}
