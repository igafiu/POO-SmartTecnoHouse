package modelo;

public class SensorLuz extends Sensor {

    public SensorLuz() {
        super("LGH-001", "Sensor de Luz", "lux");
    }

    @Override
    public void actualizarValor() {
        valorActual = random.nextInt(1000);
    }
}
