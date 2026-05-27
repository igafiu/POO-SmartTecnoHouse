package modelo;

import java.util.List;

public interface Regla {

    String getID();

    String getDescripcion();

    boolean isActiva();

    void setActiva(boolean activa);

    void aplicar(List<Sensor> sensores, List<Actuador> actuadores);
}
