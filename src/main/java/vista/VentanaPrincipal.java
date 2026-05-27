package vista;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    private final PanelSensores panelSensores;
    private final PanelActuadores panelActuadores;
    private final PanelReglas panelReglas;
    private final PanelLogs panelLogs;


    public VentanaPrincipal() {
        super("SmartTecnoHouse");
        setSize(900, 620);
        setLocationRelativeTo(null);

        panelSensores = new PanelSensores();
        panelActuadores = new PanelActuadores();
        panelReglas = new PanelReglas();
        panelLogs = new PanelLogs();

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Sensores", panelSensores);
        pestanas.addTab("Actuadores", panelActuadores);
        pestanas.addTab("Reglas", panelReglas);
        pestanas.addTab("Logs", panelLogs);

        add(pestanas);
    }
}
