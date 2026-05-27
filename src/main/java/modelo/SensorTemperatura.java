package modelo;

public class SensorTemperatura extends Sensor {

    public SensorTemperatura() {
        super("TMP-001", "Sensor de Temperatura", "°C");
    }

    @Override
    public void actualizarValor() {
        valorActual = Math.round((15 + random.nextDouble() * 20) * 10.0) / 10.0;
    }
}
