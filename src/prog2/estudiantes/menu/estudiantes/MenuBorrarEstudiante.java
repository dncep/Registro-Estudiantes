package prog2.estudiantes.menu.estudiantes;

import prog2.estudiantes.data.Estudiante;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Seccion;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuBorrarEstudiante implements Menu {
    @Override
    public String getNombre() {
        return "Borrar estudiante";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        Estudiante est = registro.getEstudiante("Introduzca el ID del estudiante", scanner);

        registro.estudiantes.remove(est);

        System.out.println("Estudiante " + est.nombre + " " + est.apellido + " ha sido borrado");

        //Borrar de secciones
        for(Seccion sec : registro.secciones) {
            if(sec.estudiantes.remove(est)) {
                System.out.println("    Fue borrado de la sección " + sec.codigo + " - " + sec.trimestre);
            }
        }
        return true;
    }
}
