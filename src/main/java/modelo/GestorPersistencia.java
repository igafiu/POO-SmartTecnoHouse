package modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GestorPersistencia {

    private static final String FICHERO = "estado.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private GestorPersistencia() {

    }

    public static void guardar(SmartTecnoHouse sistema) {
        Map<String, Object> estado = new HashMap<>();

        Map<String, String> estadoActuadores = new HashMap<>();
        for (Actuador actuador : sistema.getActuadores()) {
            estadoActuadores.put(actuador.getID(), actuador.getEstadoActual());
        }

        Map<String, Boolean> estadoReglas = new HashMap<>();
        for (IRegla regla : sistema.getReglas()) {
            estadoReglas.put(regla.getID(), regla.isActiva());
        }

        estado.put("actuadores", estadoActuadores);
        estado.put("reglas", estadoReglas);

        try (Writer buffer = new FileWriter(FICHERO)) {
            gson.toJson(estado, buffer);
        } catch (IOException e) {
            System.err.println("Error al guardar estado: " + e.getMessage());
        }
    }

    public static void cargar(SmartTecnoHouse sistema) {
        File fichero = new File(FICHERO);
        if (!fichero.exists()) return;

        try (Reader lector = new FileReader(fichero)) {
            Type tipo = new TypeToken<Map<String, Object>>() {}.getType();
            Map<String, Object> estado = gson.fromJson(lector, tipo);
            if (estado == null) return;

            Object registro = estado.get("actuadores");
            if (registro instanceof Map<?, ?> mapa) {
                for (Map.Entry<?, ?> e : mapa.entrySet()) { // todo: Volver a intentar asignar los genéricos que corresponden
                    Actuador a = sistema.getActuadorPorID((String) e.getKey());
                    if (a != null) a.setEstado((String) e.getValue());
                }
            }

            registro = estado.get("reglas");
            if (registro instanceof Map<?, ?> mapa) {
                for (Map.Entry<?, ?> e : mapa.entrySet()) { // todo: Volver a intentar asignar los genéricos que corresponden
                    for (IRegla reg : sistema.getReglas()) {
                        if (reg.getID().equals(e.getKey())) {
                            reg.setActiva((Boolean) e.getValue());
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error al cargar estado: " + e.getMessage());
        }
    }
}
