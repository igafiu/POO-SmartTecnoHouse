package controlador;

import modelo.IRegla;
import modelo.Sensor;
import modelo.SmartTecnoHouse;
import vista.VentanaPrincipal;

import java.util.List;

public class Controlador {

    SmartTecnoHouse sistema;
    VentanaPrincipal ventana;

    public Controlador(SmartTecnoHouse sistema, VentanaPrincipal ventana) {
        this.sistema = sistema;
        this.ventana = ventana;
        inicializarPaneles();
    }

    private void inicializarPaneles(){
        actualizarListaSensores();
        actualizarListaReglas();
        registrarListeners();
    }

    private void actualizarListaSensores(){
        List<Sensor> sensores = sistema.getSensores();
        String[][] datos = new String[sensores.size()][3];
        for (int i = 0; i < sensores.size(); i++) {
            Sensor sensor = sensores.get(i);
            sensor.actualizarValor();
            datos[i][0] = sensor.getID();
            datos[i][1] = sensor.getNombre();
            datos[i][2] = sensor.getEstadoActual();
        }
        ventana.getPanelSensores().refrescarTabla(datos);
    }

    private void actualizarListaReglas(){
        List<IRegla> reglas = sistema.getReglas();
        String[][] datos = new String[reglas.size()][3];
        for (int i = 0; i < reglas.size(); i++) {
            IRegla regla = reglas.get(i);
            datos[i][0] = regla.getID();
            datos[i][1] = regla.getDescripcion();
            datos[i][2] = regla.isActiva() ? "ACTIVA" : "INACTIVA";
        }
        ventana.getPanelReglas().refrescarTabla(datos);
    }

    private void registrarListeners(){
        ventana.getPanelSensores().getBotonActualizar().addActionListener(e -> {
            sistema.actualizarSensores();
            actualizarListaSensores();
        });
    }
}
