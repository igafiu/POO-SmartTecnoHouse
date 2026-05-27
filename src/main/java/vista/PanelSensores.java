package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelSensores extends PanelBase {

    public PanelSensores() {
        setLayout(new BorderLayout(5, 5));

        String[] columnas = {"ID", "Nombre", "Valor actual"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        JTable tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setRowHeight(24);

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
