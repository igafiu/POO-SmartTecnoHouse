package controlador;

import modelo.Actuador;
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
        refrescarSensores();
        refrescarReglas();
    }

    private void refrescarSensores(){
        List<Sensor> sensores = sistema.getSensores();
        String[][] datos = new String[sensores.size()][3];
        for (int i = 0; i < sensores.size(); i++) {
            Sensor s = sensores.get(i);
            datos[i][0] = s.getID();
            datos[i][1] = s.getNombre();
            datos[i][2] = s.getEstadoActual();
        }
        ventana.getPanelSensores().refrescarTabla(datos);
    }

    private void refrescarReglas(){
        List<IRegla> reglas = sistema.getReglas();
        String[][] datos = new String[reglas.size()][3];
        for (int i = 0; i < reglas.size(); i++) {
            IRegla r = reglas.get(i);
            datos[i][0] = r.getID();
            datos[i][1] = r.getDescripcion();
            datos[i][2] = r.isActiva() ? "ACTIVA" : "INACTIVA";
        }
        ventana.getPanelReglas().refrescarTabla(datos);
    }
}
