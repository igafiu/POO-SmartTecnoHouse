package vista;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    public PanelSensores getPanelSensores() {
        return panelSensores;
    }

    public PanelReglas getPanelReglas() {
        return panelReglas;
    }

    public PanelActuadores getPanelActuadores() {
        return panelActuadores;
    }

    public PanelLogs getPanelLogs() {
        return panelLogs;
    }

    /**
     * El controlador registra aquí lo que debe
     * ocurrir al cerrar la ventana
     */
    public void setAccionAlCerrar(Runnable accion) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                accion.run();
            }
        });
    }
}
