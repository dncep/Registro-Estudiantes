package prog2.estudiantes.menu.seccion;

import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Seccion;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuListarSecciones implements Menu {
    @Override
    public String getNombre() {
        return "Listar Secciones";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        if(registro.secciones.isEmpty()) {
            System.out.println("No hay secciones registradas");
            return true;
        }

        System.out.println("Secciones (" + registro.secciones.size() + "):");

        for(Seccion sec : registro.secciones) {
            System.out.println(sec.codigo + " - Trimestre " + sec.trimestre);
            System.out.println("    Profesor: " + sec.profesor);
            System.out.println("    Aula: " + sec.aula);
            System.out.println("    Materia: " + sec.materia);
            System.out.println("    Estudiantes: " + sec.estudiantes.size());
            System.out.println("    Horario: " + sec.horario);
            System.out.println();
        }


        return true;
    }
}
