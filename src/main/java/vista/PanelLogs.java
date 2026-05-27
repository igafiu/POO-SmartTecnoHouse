package vista;

import javax.swing.*;
import java.awt.*;

public class PanelLogs extends PanelBase {

    private final JTextArea areaTexto;
    private final JButton btnGuardar;

    public PanelLogs() {
        formatoLayout();
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        add(new JScrollPane(areaTexto), BorderLayout.CENTER);

        btnGuardar = new JButton("Guardar log");
        JPanel sur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sur.add(btnGuardar);
        add(sur, BorderLayout.SOUTH);
    }

    public void appendEntrada(String linea) {
        areaTexto.append(linea + "\n");
        areaTexto.setCaretPosition(areaTexto.getDocument().getLength());
    }

    public void limpiar() {
        areaTexto.setText("");
    }

    public JButton getBotonGuardar() {
        return btnGuardar;
    }

}
