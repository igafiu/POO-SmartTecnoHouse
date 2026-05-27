package modelo;

import java.util.List;

public abstract class Regla implements IRegla {

    public Sensor buscarSensor(List<Sensor> lista, String id) {
        for (Sensor s : lista) {
            if (s.getID().equals(id)) return s;
        }
        return null;
    }

    public Actuador buscarActuador(List<Actuador> lista, String id) {
        for (Actuador a : lista) {
            if (a.getID().equals(id)) return a;
        }
        return null;
    }
}
