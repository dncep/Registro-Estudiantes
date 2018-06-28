package prog2.estudiantes.menu.reporteria;

import prog2.estudiantes.data.Seccion;
import prog2.estudiantes.menu.Menu;
import prog2.estudiantes.menu.MenuEstandar;
import prog2.estudiantes.menu.MenuSalir;

import java.util.Arrays;
import java.util.List;

public class MenuReporteriaSub implements MenuEstandar {

    private final Seccion seccion;
    private final List<Menu> opciones;

    public MenuReporteriaSub(Seccion seccion) {
        this.seccion = seccion;

        this.opciones = Arrays.asList(new MenuReporteriaPDF(seccion), new MenuReporteriaExcel(seccion), new MenuReporteriaCSV(seccion), new MenuSalir("Atrás"));
    }

    @Override
    public String getNombre() {
        return "Formatos de reportería - " + seccion;
    }

    @Override
    public List<Menu> getOpciones() {
        return opciones;
    }
}
