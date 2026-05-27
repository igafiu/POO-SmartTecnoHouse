package modelo;

public class SensorHumedad extends Sensor {

    public SensorHumedad() {
        super("HUM-001", "Sensor de Humedad", "%");
    }

    @Override
    public void actualizarValor() {
        // todo Calcular con random
    }
}
