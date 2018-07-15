package prog2.estudiantes.menu.estudiantes;

import prog2.estudiantes.menu.Menu;
import prog2.estudiantes.menu.MenuEstandar;
import prog2.estudiantes.menu.MenuSalir;

import java.util.Arrays;
import java.util.List;

public class MenuEstudiantes implements Menu, MenuEstandar {

    private List<Menu> opciones = Arrays.asList(new MenuCrearEstudiante(), new MenuListarEstudiantes(), new MenuEditarEstudiante(), new MenuBorrarEstudiante(), new MenuBuscarEstudiante(), new MenuSalir("Atrás"));

    @Override
    public String getNombre() {
        return "Estudiantes";
    }

    @Override
    public List<Menu> getOpciones() {
        return opciones;
    }
}
