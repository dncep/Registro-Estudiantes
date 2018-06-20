package prog2.estudiantes.menu.seccion;

import prog2.estudiantes.data.Estado;
import prog2.estudiantes.data.Estudiante;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Seccion;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuAgregarEstudianteSeccion implements Menu {
    @Override
    public String getNombre() {
        return "Agregar Estudiante";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        Estudiante agregar = null;

        while (true) {
            int id = Entrada.getInt("Introduzca el ID/matrícula del estudiante", scanner);
            for (Estudiante est : registro.estudiantes) {
                if (est.id == id) {
                    agregar = est;
                }
            }
            if (agregar == null) {
                System.out.print("El ID/matricula del estudiante que usted ingresó no existe");
            } else if (agregar.estado == Estado.INACTIVO) {
                System.out.println("Solo se pueden agregar estudiantes activos");
            } else break;
        }
        while(true) {
            int idSec = Entrada.getInt("Introduzca el ID de la seccion", scanner);
            for (Seccion sec : registro.secciones) {
                if (sec.id == idSec) {
                    sec.estudiantes.add(agregar);
                    System.out.println("Estudiante " + agregar.nombre + " " + agregar.apellido + " fue agregado a la sección " + sec.codigo + " - " + sec.trimestre);
                    return true;
                }
            }
            System.out.println("No se encontró una sección con ese código");
        }
    }
}