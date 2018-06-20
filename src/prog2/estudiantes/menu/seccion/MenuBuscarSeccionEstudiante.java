package prog2.estudiantes.menu.seccion;

import prog2.estudiantes.data.Estudiante;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Seccion;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuBuscarSeccionEstudiante implements Menu {
    @Override
    public String getNombre() {
        return "Buscar por Estudiante";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        int idEst = Entrada.getInt("Introduzca el ID del estudiante", scanner);

        Estudiante estudiante = null;
        for(Estudiante est : registro.estudiantes) {
            if(est.id == idEst) {
                estudiante = est;
                break;
            }
        }

        if(estudiante == null) {
            System.out.println("No se encontró ese estudiante");
            return true;
        }

        boolean encontrado = false;

        for(Seccion sec : registro.secciones) {
            if(sec.estudiantes.contains(estudiante)) {
                System.out.println(sec.codigo + " " + sec.trimestre + " (ID: " + sec.id + ")");
                System.out.println("\tProfesor: " + sec.profesor);
                System.out.println("\tAula: " + sec.aula);
                System.out.println("\tMateria: " + sec.materia);
                System.out.println("\tTrimestre: " + sec.trimestre);
                System.out.println("\tHorario: " + sec.horario);
                System.out.println("\tCodigo: " + sec.codigo);
                System.out.println();
                encontrado = true;
            }
        }

        if(!encontrado) System.out.println("No se encontró sección para el estudiante " + estudiante.nombre + " " + estudiante.apellido + " (" + estudiante.id + ")");

        return true;
    }
}
