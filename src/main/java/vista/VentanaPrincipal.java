package vista;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    private final PanelSensores panelSensores;

    public VentanaPrincipal() {
        super("SmartTecnoHouse");
        setSize(900, 620);
        setLocationRelativeTo(null);

        panelSensores = new PanelSensores();
        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Sensores", panelSensores);
        add(pestanas);
    }
}
