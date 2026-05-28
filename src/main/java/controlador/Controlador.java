package controlador;

import modelo.*;
import vista.VentanaPrincipal;

import javax.swing.*;
import java.util.List;

/**
 * Controlador principal de la aplicación SmartTecnoHouse.
 * Actúa como intermediario entre el modelo SmartTecnoHouse y la vista
 * VentanaPrincipal.
 * <p>
 * Registra los listeners de todos los botones y
 * listas de la interfaz, procesa las acciones del usuario y actualiza la vista
 * con los datos más recientes del modelo.
 * </p>
 * <p>
 * Al iniciarse, carga el estado persistido desde estado.json y rellena
 * todos los paneles con los valores actuales del sistema.
 * </p>
 */
public class Controlador {

    SmartTecnoHouse sistema;
    VentanaPrincipal ventana;

    /**
     * Crea el controlador, carga el estado persistido y registra todos los
     * listeners de la interfaz gráfica.
     *
     * @param sistema instancia única de SmartTecnoHouse (modelo)
     * @param ventana ventana principal de la aplicación (vista)
     */
    public Controlador(SmartTecnoHouse sistema, VentanaPrincipal ventana) {
        this.sistema = sistema;
        this.ventana = ventana;

        GestorPersistencia.cargar(sistema);
        registrarListeners();
        inicializarPaneles();
    }

    /**
     * Genera una lectura inicial de todos los sensores y rellena los tres
     * paneles (sensores, reglas y actuadores) con los datos del modelo.
     * Además, selecciona el primer actuador de la lista para que el combo
     * de acciones no quede vacío al arrancar.
     */
    private void inicializarPaneles() {
        sistema.actualizarSensores();
        actualizarListaSensores();
        actualizarListaReglas();
        actualizarListaActuadores();

        if (!sistema.getActuadores().isEmpty()) {
            ventana.getPanelActuadores().getListaActuadores().setSelectedIndex(0);
        }
    }

    /**
     * Lee los valores actuales de todos los sensores del modelo y actualiza
     * la tabla del PanelSensores con ID, nombre y valor actual.
     */
    private void actualizarListaSensores() {
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

    /**
     * Lee la lista de reglas del modelo y actualiza la tabla del
     * PanelReglas con ID, descripción y estado (ACTIVA / INACTIVA).
     */
    private void actualizarListaReglas() {
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

    /**
     * Lee la lista de actuadores del modelo y actualiza el JList del
     * PanelActuadores mostrando el nombre y el estado actual de cada uno.
     */
    private void actualizarListaActuadores() {
        List<Actuador> actuadores = sistema.getActuadores();
        String[] etiquetas = new String[actuadores.size()];
        for (int i = 0; i < actuadores.size(); i++) {
            Actuador a = actuadores.get(i);
            etiquetas[i] = a.getNombre() + "  (" + a.getEstadoActual() + ")";
        }
        ventana.getPanelActuadores().refrescarActuadores(etiquetas);
    }

    /**
     * Registra todos los ActionListener y ListSelectionListener
     * de la interfaz gráfica.
     * <ul>
     *   <li>Botón «Actualizar lecturas»: genera nuevos valores aleatorios en los sensores.</li>
     *   <li>Botón «Activar / Desactivar»: invierte el estado de la regla seleccionada.</li>
     *   <li>Botón «Evaluar reglas»: ejecuta todas las reglas activas sobre el estado actual.</li>
     *   <li>Selección en la lista de actuadores: actualiza el combo de acciones disponibles.</li>
     *   <li>Botón «Ejecutar»: dispara la acción seleccionada con fuente MANUAL.</li>
     *   <li>Botón «Guardar log»: escribe el log en disco.</li>
     *   <li>Cierre de ventana: persiste el estado en estado.json y genera el log.</li>
     * </ul>
     */
    private void registrarListeners() {

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

    /**
     * Añade al panel de logs las entradas del GestorLogs generadas a
     * partir de la posición desde, que corresponde al número de entradas
     * existentes antes de la última operación sobre el modelo.
     *
     * @param desde índice desde el que empezar a leer las entradas nuevas
     */
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
