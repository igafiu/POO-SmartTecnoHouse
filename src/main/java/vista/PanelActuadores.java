package vista;

import javax.swing.*;
import java.awt.*;

public class PanelActuadores extends PanelBase {

    private final DefaultListModel<String> modeloLista;
    private final JList<String> listaActuadores;
    private final JComboBox<String> comboAcciones;
    private final JButton btnEjecutar;

    public PanelActuadores() {
        formatoLayout();

        modeloLista     = new DefaultListModel<>();
        listaActuadores = new JList<>(modeloLista);
        listaActuadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollLista = new JScrollPane(listaActuadores);
        scrollLista.setPreferredSize(new Dimension(230, 0));
        scrollLista.setBorder(BorderFactory.createTitledBorder("Actuadores"));
        add(scrollLista, BorderLayout.WEST);

        comboAcciones = new JComboBox<>();
        btnEjecutar   = new JButton("Ejecutar");

        JPanel panelControles = new JPanel();
        panelControles.setLayout(new BoxLayout(panelControles, BoxLayout.Y_AXIS));
        panelControles.setBorder(BorderFactory.createTitledBorder("Control"));

        JPanel filaAccion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filaAccion.add(new JLabel("Acción:"));
        filaAccion.add(comboAcciones);

        JPanel filaBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filaBoton.add(btnEjecutar);

        panelControles.add(Box.createVerticalStrut(10));
        panelControles.add(filaAccion);
        panelControles.add(Box.createVerticalStrut(8));
        panelControles.add(filaBoton);

        add(panelControles, BorderLayout.CENTER);
    }

    public void refrescarActuadores(String[] etiquetas) {
        int indiceAnterior = listaActuadores.getSelectedIndex();
        modeloLista.clear();
        for (String e : etiquetas) {
            modeloLista.addElement(e);
        }
        if (indiceAnterior >= 0 && indiceAnterior < modeloLista.size()) {
            listaActuadores.setSelectedIndex(indiceAnterior);
        }
    }

    public void actualizarAcciones(String[] acciones) {
        comboAcciones.removeAllItems();
        for (String a : acciones) {
            comboAcciones.addItem(a);
        }
    }

    public JList<String> getListaActuadores() {
        return listaActuadores;
    }

    public JButton getBotonEjecutar() {
        return btnEjecutar;
    }

    public String getAccionSeleccionada() {
        Object item = comboAcciones.getSelectedItem();
        return item != null ? item.toString() : null;
    }

    public int getIndiceActuadorSeleccionado() {
        return listaActuadores.getSelectedIndex();
    }
}
