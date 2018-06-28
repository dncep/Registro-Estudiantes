package prog2.estudiantes.menu.reporteria;

import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Seccion;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuReporteriaExcel implements Menu {
    private final Seccion seccion;

    public MenuReporteriaExcel(Seccion seccion) {
        this.seccion = seccion;
    }

    @Override
    public String getNombre() {
        return "Excel";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        return true;
    }
}
