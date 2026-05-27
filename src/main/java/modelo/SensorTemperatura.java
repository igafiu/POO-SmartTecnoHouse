package modelo;

public class SensorTemperatura extends Sensor {

    public SensorTemperatura() {
        super("TMP-001", "Sensor de Temperatura", "°C");
    }

    @Override
    public void actualizarValor() {
        // todo calcular un random adecuado
    }
}
