package prog2.estudiantes.menu;

import prog2.estudiantes.data.Registro;

import java.util.Scanner;

public class MenuSalir implements Menu {

    private String nombre;

    public MenuSalir(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        return false;
    }
}
