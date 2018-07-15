package prog2.estudiantes.menu.seccion;

import prog2.estudiantes.data.Registro;
import prog2.estudiantes.data.Seccion;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuBuscarSeccionMateria implements Menu {
    @Override
    public String getNombre() {
        return "Buscar por Materia";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        ArrayList<Seccion> secciones = registro.secciones;
        String busca = Entrada.getString("Introduzca el codigo de la materia", scanner);
        boolean encontrado = false;
        for(Seccion sec : secciones) {
            if (sec.materia.codigo.equalsIgnoreCase(busca)) {
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
        if (!encontrado) {
            System.out.println("Materia no encontrada!");
        }
        return true;
    }
}