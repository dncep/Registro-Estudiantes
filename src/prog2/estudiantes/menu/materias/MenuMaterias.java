package prog2.estudiantes.menu.materias;

import prog2.estudiantes.menu.Menu;
import prog2.estudiantes.menu.MenuEstandar;
import prog2.estudiantes.menu.MenuSalir;

import java.util.Arrays;
import java.util.List;

public class MenuMaterias implements Menu, MenuEstandar {

    private List<Menu> opciones = Arrays.asList(new MenuCrearMateria(), new MenuListarMaterias(), new MenuEditarMateria(), new MenuBorrarMateria(), new MenuBuscarMateria(), new MenuSalir("Atrás"));

    @Override
    public String getNombre() {
        return "Materias";
    }

    @Override
    public List<Menu> getOpciones() {
        return opciones;
    }
}
