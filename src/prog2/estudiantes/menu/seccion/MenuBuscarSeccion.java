package prog2.estudiantes.menu.seccion;

import prog2.estudiantes.menu.Menu;
import prog2.estudiantes.menu.MenuEstandar;
import prog2.estudiantes.menu.MenuSalir;

import java.util.Arrays;
import java.util.List;

public class MenuBuscarSeccion implements MenuEstandar {

    private List<Menu> opciones = Arrays.asList(
            new MenuBuscarSeccionEstudiante(),
            new MenuBuscarSeccionMateria(),
            new MenuSalir("Atrás", true));

    @Override
    public List<Menu> getOpciones() {
        return opciones;
    }

    @Override
    public String getNombre() {
        return "Buscar Secciones";
    }
}
