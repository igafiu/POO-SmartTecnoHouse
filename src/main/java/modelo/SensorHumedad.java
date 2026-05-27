package modelo;

public class SensorHumedad extends Sensor {

    public SensorHumedad() {
        super("HUM-001", "Sensor de Humedad", "%");
    }

    @Override
    public void actualizarValor() {
        valorActual = Math.round((20 + random.nextDouble() * 70) * 10.0) / 10.0;
    }
}
