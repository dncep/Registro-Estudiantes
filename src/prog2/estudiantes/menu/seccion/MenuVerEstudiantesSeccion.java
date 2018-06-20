package prog2.estudiantes.menu.seccion;

import prog2.estudiantes.data.Estudiante;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Seccion;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuVerEstudiantesSeccion implements Menu {
    @Override
    public String getNombre() {
        return "Ver Estudiantes";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        int idSec = Entrada.getInt("Introduzca el ID de la sección", scanner);
        for(Seccion sec : registro.secciones) {
            if(sec.id == idSec) {
                System.out.println(
                        sec.codigo + " - " + sec.trimestre + ": " +
                                sec.estudiantes.size() + " estudiante" + ((sec.estudiantes.size() != 1) ? "s" : ""));
                for(Estudiante est : sec.estudiantes) {
                    System.out.println("    " + est.nombre + " " + est.apellido + " (ID: " + est.id + ")");
                }
            }
        }
        return true;
    }
}
