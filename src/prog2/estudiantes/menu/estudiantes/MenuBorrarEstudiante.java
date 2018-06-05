package prog2.estudiantes.menu.estudiantes;

import prog2.estudiantes.data.Estudiante;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuBorrarEstudiante implements Menu {
    @Override
    public String getNombre() {
        return "Borrar estudiante";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        ArrayList<Estudiante> estudiantes = registro.estudiantes;
        int id = Entrada.getInt("Introduzca el ID/matrícula del estudiante", scanner);
        for(int i = 0; i < estudiantes.size(); i++) {
            Estudiante est = estudiantes.get(i);
            if(est.id == id) {
                estudiantes.remove(i);
                System.out.println("Estudiante " + est.nombre + " " + est.apellido + " ha sido borrado");
                return true;
            }
        }
        System.out.println("Estudiante no encontrado");
        return true;
    }
}
