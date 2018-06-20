package prog2.estudiantes.menu;

import prog2.estudiantes.menu.estudiantes.MenuEstudiantes;
import prog2.estudiantes.menu.materias.MenuMaterias;
import prog2.estudiantes.menu.seccion.MenuSecciones;

import java.util.Arrays;
import java.util.List;

public class MenuInicio implements Menu, MenuEstandar {

    private List<Menu> opciones = Arrays.asList(
            new MenuBucle(new MenuEstudiantes()),
            new MenuBucle(new MenuMaterias()),
            new MenuBucle(new MenuSecciones()),
            new MenuSalir("Guardar y salir"));

    @Override
    public String getNombre() {
        return "Inicio";
    }

    @Override
    public List<Menu> getOpciones() {
        return opciones;
    }
}
