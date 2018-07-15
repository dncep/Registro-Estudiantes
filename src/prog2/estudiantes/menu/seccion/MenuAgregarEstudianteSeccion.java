package prog2.estudiantes.menu.seccion;

import prog2.estudiantes.data.Estado;
import prog2.estudiantes.data.Estudiante;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Seccion;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuAgregarEstudianteSeccion implements Menu {
    @Override
    public String getNombre() {
        return "Agregar Estudiante";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        Estudiante est;

        while (true) {
            est = registro.getEstudiante("Introduzca el ID del estudiante", scanner);
            if (est.estado == Estado.INACTIVO) {
                System.out.println("No se pueden agregar estudiantes inactivos");
            } else {
                int registrados = 0;
                for(Seccion sec : registro.secciones) {
                    if(sec.estudiantes.contains(est)) registrados++;
                }
                if(registrados >= 5) {
                    System.out.println("Un estudiante no puede estar registrado en más de 5 secciones");
                    return true;
                } else break;
            }
        }
        Seccion sec = registro.getSeccion("Introduzca el ID de la sección", scanner);
        if(!sec.estudiantes.contains(est)) {

            int registradosArea = 0;
            for(Seccion otra : registro.secciones) {
                if(otra.materia.area == sec.materia.area && otra.estudiantes.contains(est)) {
                    registradosArea++;
                    if(registradosArea >= 2) {
                        System.out.println("Un estudiante no puede estar registrado en más de 2 secciones de la misma área");
                        return true;
                    }
                }
            }

            sec.estudiantes.add(est);
            System.out.println("Estudiante " + est.nombre + " " + est.apellido + " fue agregado a la sección " + sec);
        } else {
            System.out.println("Estudiante" + est.nombre + " " + est.apellido + " ya existe en la sección " + sec);
        }
        return true;
    }
}