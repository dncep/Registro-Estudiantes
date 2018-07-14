package prog2.estudiantes.menu.integracion;

import prog2.estudiantes.menu.Menu;
import prog2.estudiantes.menu.MenuEstandar;

import java.util.Arrays;
import java.util.List;

public class MenuImportar implements MenuEstandar {

    private List<Menu> opciones = Arrays.asList(new MenuImportarEstudiantes(), new MenuImportarMaterias());
    @Override
    public List<Menu> getOpciones() {
        return opciones;
    }

    @Override
    public String getNombre() {
        return "Importar";
    }
}
