package prog2.estudiantes.menu.materias;

import prog2.estudiantes.data.Materia;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuBorrarMateria implements Menu {
    @Override
    public String getNombre() {
        return "Borrar Materia";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        Materia mat = registro.getMateria("Introduzca el código de la materia", scanner);
        registro.materias.remove(mat);
        System.out.println("Materia " + mat.nombre + " ha sido borrada");

        //Borrar seccion
        registro.secciones.removeIf(s -> {
            if(s.materia == mat) {
                System.out.println("    Fue borrada la sección " + s.codigo + " - " + s.trimestre);
                return true;
            }
            return false;
        });
        return true;
    }
}