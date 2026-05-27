package controlador;

import modelo.SmartTecnoHouse;
import vista.VentanaPrincipal;

public class Controlador {

    SmartTecnoHouse sistema;
    VentanaPrincipal ventana;

    public Controlador(SmartTecnoHouse sistema, VentanaPrincipal ventana) {
        this.sistema = sistema;
        this.ventana = ventana;
        inicializarPaneles();
    }

    private void inicializarPaneles(){
        System.out.println(sistema.getActuadores().toString());
    }
}
