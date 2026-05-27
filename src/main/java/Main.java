import controlador.Controlador;
import modelo.SmartTecnoHouse;
import vista.VentanaPrincipal;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            SmartTecnoHouse sistema = SmartTecnoHouse.getInstance();

            new Controlador(sistema, ventana);
            ventana.setVisible(true);
        });
    }

}
