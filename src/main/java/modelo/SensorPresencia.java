package modelo;

public class SensorPresencia extends Sensor {

    public SensorPresencia() {
        super("PIR-001", "Sensor de Presencia", "");
    }

    @Override
    public void actualizarValor() {
        valorActual = random.nextInt(2);
    }

    @Override
    public String getEstadoActual() {
        return valorActual == 1 ? "Detectado" : "No detectado";
    }
}
