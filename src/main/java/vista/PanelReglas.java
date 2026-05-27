package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelReglas extends PanelBase {

    private final JButton btnToggle;
    private final JButton btnEvaluar;
    private final JTable tabla;

    public PanelReglas() {
        formatoLayout();

        String[] columnas = {"ID", "Descripción", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setRowHeight(24);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(380);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(90);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnToggle  = new JButton("Activar / Desactivar");
        btnEvaluar = new JButton("Evaluar reglas");

        JPanel sur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sur.add(btnToggle);
        sur.add(btnEvaluar);
        add(sur, BorderLayout.SOUTH);
    }

    public JButton getBotonToggle() {
        return btnToggle;
    }

    public JButton getBotonEvaluar() {
        return btnEvaluar;
    }

    public String getIDReglaSeleccionada() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) return null;
        return (String) modeloTabla.getValueAt(fila, 0);
    }
}
