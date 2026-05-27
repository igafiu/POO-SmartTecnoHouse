package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelSensores extends PanelBase {

    protected JButton btnActualizar;

    public PanelSensores() {
        formatoLayout();

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

        btnActualizar = new JButton("Actualizar lecturas");
        JPanel sur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sur.add(btnActualizar);
        add(sur, BorderLayout.SOUTH);

    }

    public JButton getBotonActualizar() {
        return btnActualizar;
    }
}
