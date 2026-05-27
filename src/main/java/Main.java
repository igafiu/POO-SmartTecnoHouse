import modelo.GestorLogs;

public class Main {

    public static void main(String[] args) {
        GestorLogs instance = GestorLogs.getInstance();
        instance.addLog("PRUEBA", "ON", "MANUAL");
        instance.guardarLog();
        System.out.println("compila...");
    }

}
