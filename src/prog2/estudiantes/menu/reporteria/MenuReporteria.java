package prog2.estudiantes.menu.reporteria;

import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Seccion;
import prog2.estudiantes.menu.Menu;
import prog2.estudiantes.menu.MenuBucle;

import java.util.Scanner;

public class MenuReporteria implements Menu {
    @Override
    public String getNombre() {
        return "Reportería";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        Seccion sec = registro.getSeccion("Introduzca el ID de la sección para generar el reporte", scanner);

        return new MenuBucle(new MenuReporteriaSub(sec)).seleccionar(registro, scanner);
    }
}
