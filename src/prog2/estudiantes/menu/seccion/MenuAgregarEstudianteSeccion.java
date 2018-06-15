package prog2.estudiantes.menu.seccion;

import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuAgregarEstudianteSeccion implements Menu {
    @Override
    public String getNombre() {
        return "Agregar Estudiante";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        return true;
    }
}
