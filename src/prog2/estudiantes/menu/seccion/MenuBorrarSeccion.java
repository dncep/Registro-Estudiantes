package prog2.estudiantes.menu.seccion;

import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuBorrarSeccion implements Menu {
    @Override
    public String getNombre() {
        return "Borrar Sección";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        return true;
    }
}
