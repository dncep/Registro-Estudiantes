package prog2.estudiantes.menu.seccion;

import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Seccion;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuBorrarSeccion implements Menu {
    @Override
    public String getNombre() {
        return "Borrar Sección";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        Seccion sec = registro.getSeccion("Introduzca el ID de la sección", scanner);
        registro.secciones.remove(sec);
        System.out.println("La sección " + sec.codigo + " " + sec.trimestre + " ha sido borrada");
        return true;
    }
}