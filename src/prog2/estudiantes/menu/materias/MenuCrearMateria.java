package prog2.estudiantes.menu.materias;

import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuCrearMateria implements Menu {
    @Override
    public String getNombre() {
        return "Crear Materia";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        return true;
    }
}
