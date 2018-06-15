package prog2.estudiantes.menu;

import prog2.estudiantes.data.Registro;

import java.util.Scanner;

public class MenuSalir implements Menu {

    private String nombre;
    private boolean ret;

    public MenuSalir(String nombre) {
        this(nombre, false);
    }

    public MenuSalir(String nombre, boolean ret) {
        this.nombre = nombre;
        this.ret = ret;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        return ret;
    }
}
