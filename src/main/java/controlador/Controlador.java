package controlador;

import modelo.*;
import vista.VentanaPrincipal;

import javax.swing.*;
import java.util.List;

public class Controlador {

    SmartTecnoHouse sistema;
    VentanaPrincipal ventana;

    public Controlador(SmartTecnoHouse sistema, VentanaPrincipal ventana) {
        this.sistema = sistema;
        this.ventana = ventana;

        GestorPersistencia.cargar(sistema);
        registrarListeners();
        inicializarPaneles();
    }

    private void inicializarPaneles(){
        sistema.actualizarSensores();
        actualizarListaSensores();
        actualizarListaReglas();
        actualizarListaActuadores();

        /**
         * Si el actuador no está seleccionado,
         * el desplegable se queda vacío.
         * Tareas para mañana:
         * - Mejorar aspectos SOLID y DRY
         * - Hacer Testing end to end
         * - Documentar
         *
         */
        if (!sistema.getActuadores().isEmpty()) {
            ventana.getPanelActuadores().getListaActuadores().setSelectedIndex(0);
        }
    }

    private void actualizarListaSensores(){
        List<Sensor> sensores = sistema.getSensores();
        String[][] datos = new String[sensores.size()][3];
        for (int i = 0; i < sensores.size(); i++) {
            Sensor sensor = sensores.get(i);
            datos[i][0] = sensor.getID();
            datos[i][1] = sensor.getNombre();
            datos[i][2] = sensor.getEstadoActual();
        }
        ventana.getPanelSensores().refrescarTabla(datos);
    }

    private void actualizarListaReglas(){
        List<IRegla> reglas = sistema.getReglas();
        String[][] datos = new String[reglas.size()][3];
        for (int i = 0; i < reglas.size(); i++) {
            IRegla regla = reglas.get(i);
            datos[i][0] = regla.getID();
            datos[i][1] = regla.getDescripcion();
            datos[i][2] = regla.isActiva() ? "ACTIVA" : "INACTIVA";
        }
        ventana.getPanelReglas().refrescarTabla(datos);
    }


    private void actualizarListaActuadores() {
        List<Actuador> actuadores = sistema.getActuadores();
        String[] etiquetas = new String[actuadores.size()];
        for (int i = 0; i < actuadores.size(); i++) {
            Actuador a = actuadores.get(i);
            etiquetas[i] = a.getNombre() + "  (" + a.getEstadoActual() + ")";
        }
        ventana.getPanelActuadores().refrescarActuadores(etiquetas);
    }

    private void registrarListeners(){

        // Refrescar los valores en el panel de sensores
        ventana.getPanelSensores().getBotonActualizar().addActionListener(e -> {
            sistema.actualizarSensores();
            actualizarListaSensores();
        });


        // Activa o desactiva las reglas
        ventana.getPanelReglas().getBotonToggle().addActionListener(e -> {
            String id = ventana.getPanelReglas().getIDReglaSeleccionada();
            if (id == null) {
                JOptionPane.showMessageDialog(ventana, "Selecciona una regla.");
                return;
            }
            for (IRegla regla : sistema.getReglas()) {
                if (regla.getID().equals(id)) {
                    regla.setActiva(!regla.isActiva());
                    break;
                }
            }
            actualizarListaReglas();
        });



        // Evalua todas las reglas activas
        ventana.getPanelReglas().getBotonEvaluar().addActionListener(e -> {
            int antes = GestorLogs.getInstance().getEntradas().size();
            sistema.evaluarReglas();
            actualizarListaActuadores();
            sincronizarEntradasNuevas(antes);
        });

        // Actualiza el combo de acciones al seleccionar un actuador
        ventana.getPanelActuadores().getListaActuadores().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int idx = ventana.getPanelActuadores().getIndiceActuadorSeleccionado();
            if (idx >= 0) {
                Actuador a = sistema.getActuadores().get(idx);
                ventana.getPanelActuadores().actualizarAcciones(a.getAccionesPosibles());
            }
        });

        // Operar los actuadores de manera manual
        ventana.getPanelActuadores().getBotonEjecutar().addActionListener(e -> {
            int idx    = ventana.getPanelActuadores().getIndiceActuadorSeleccionado();
            String acc = ventana.getPanelActuadores().getAccionSeleccionada();
            if (idx < 0 || acc == null) {
                JOptionPane.showMessageDialog(ventana, "Selecciona un actuador y una acción.");
                return;
            }
            int antes = GestorLogs.getInstance().getEntradas().size();
            try {
                sistema.getActuadores().get(idx).ejecutarAccion(acc, "MANUAL");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(ventana, ex.getMessage());
                return;
            }
            actualizarListaActuadores();
            sincronizarEntradasNuevas(antes);
        });


        // Logs: guardar fichero
        ventana.getPanelLogs().getBotonGuardar().addActionListener(e -> {
            GestorLogs.getInstance().guardarLog();
            JOptionPane.showMessageDialog(ventana, "Log guardado correctamente.");
        });

        // Al cerrar la ventana persistimos el estado y generamos el log
        ventana.setAccionAlCerrar(() -> {
            GestorPersistencia.guardar(sistema);
            GestorLogs.getInstance().guardarLog();
            ventana.dispose();
            System.exit(0);
        });

    }

    private void sincronizarEntradasNuevas(int desde) {
        List<String[]> entradas = GestorLogs.getInstance().getEntradas();
        for (int i = desde; i < entradas.size(); i++) {
            String[] entr = entradas.get(i);
            ventana.getPanelLogs().appendEntrada(
                    String.format("%-25s %-12s %-8s %-10s", entr[0], entr[1], entr[2], entr[3])
            );
        }
    }
}
