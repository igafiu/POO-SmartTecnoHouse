package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

abstract public class PanelBase extends JPanel {

    protected DefaultTableModel modeloTabla;

    public void refrescarTabla(String[][] datos) {
        modeloTabla.setRowCount(0);
        for (String[] fila : datos) {
            modeloTabla.addRow(fila);
        }
    }
}
