package prog2.estudiantes.menu.materias;

import prog2.estudiantes.data.Materia;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuListarMaterias implements Menu {
    @Override
    public String getNombre() {
        return "Listar Materias";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        if(registro.materias.isEmpty()) {
            System.out.println("No hay materias registradas");
        } else {
            for(Materia materia : registro.materias) {
                System.out.println(materia.codigo + " - " + materia.nombre);
                System.out.println("\tID: " + materia.id + " | Área: " + materia.area.getNombre());
            }
        }
        return true;
    }
}
