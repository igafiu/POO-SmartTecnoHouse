package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

abstract public class PanelBase extends JPanel {

    protected DefaultTableModel modeloTabla;

    public void refrescarTabla(String[][] datos) {
        modeloTabla.setRowCount(0);
        for (String[] fila : datos) {
            modeloTabla.addRow(fila);
        }
    }

    protected void formatoLayout(){
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}
