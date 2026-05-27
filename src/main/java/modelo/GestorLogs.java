package modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GestorLogs {

    private static final GestorLogs instancia = new GestorLogs();

    private static final DateTimeFormatter FMT_TS    = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final DateTimeFormatter FMT_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final List<String[]> entradas;

    private GestorLogs() {
        entradas = new ArrayList<>();
    }

    public static GestorLogs getInstance() {
        return instancia;
    }

    public void addLog(String actuatorId, String accion, String fuente) {
        String ts = LocalDateTime.now().format(FMT_TS);
        entradas.add(new String[]{ts, actuatorId, accion, fuente});
    }

    public List<String[]> getEntradas() {
        return entradas;
    }

    public void guardarLog() {
        String fecha  = LocalDateTime.now().format(FMT_FECHA);
        String nombre = "actuators-" + fecha + ".log";

        try (PrintWriter pw = new PrintWriter(new FileWriter(nombre))) {
            pw.printf("%-25s %-12s %-8s %-10s%n", "Timestamp", "Actuator", "Action", "Source");
            for (String[] e : entradas) {
                pw.printf("%-25s %-12s %-8s %-10s%n", e[0], e[1], e[2], e[3]);
            }
        } catch (IOException e) {
            System.err.println("Error al guardar el log: " + e.getMessage());
        }
    }
}
