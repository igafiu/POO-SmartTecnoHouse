package modelo;

import java.util.ArrayList;
import java.util.List;

public class SmartTecnoHouse {

    private static final SmartTecnoHouse instance = new SmartTecnoHouse();

    private final List<IRegla> reglas;
    private final List<Sensor> sensores;
    private final List<Actuador> actuadores;


    private SmartTecnoHouse() {
        sensores = new ArrayList<>();
        sensores.add(new SensorTemperatura());
        sensores.add(new SensorLuz());
        sensores.add(new SensorPresencia());
        sensores.add(new SensorHumedad());

        actuadores = new ArrayList<>();
        actuadores.add(new ActuadorBombilla());
        actuadores.add(new ActuadorPersiana());
        actuadores.add(new ActuadorVentilador());

        reglas = new ArrayList<>();
        reglas.add(new ReglaAhorroNocturno());
        reglas.add(new ReglaIluminacionAutomatica());
        reglas.add(new ReglaVentilacionConfortable());
    }

    public static SmartTecnoHouse getInstance() {
        return instance;
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    public List<Actuador> getActuadores() {
        return actuadores;
    }

    public List<IRegla> getReglas() {
        return reglas;
    }

    public void actualizarSensores() {
        for (Sensor s : sensores) {
            s.actualizarValor();
        }
    }

    public void evaluarReglas() {
        for (IRegla r : reglas) {
            if (r.isActiva()) {
                r.aplicar(sensores, actuadores);
            }
        }
    }
}
