package prog2.estudiantes.menu.estudiantes;

import prog2.estudiantes.data.Cedula;
import prog2.estudiantes.data.Estudiante;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class MenuBuscarEstudiante implements Menu {
    @Override
    public String getNombre() {
        return "Buscar estudiante";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        ArrayList<Estudiante> estudiantes = registro.estudiantes;
        String busca = Entrada.getString("Introduzca el nombre o cedula", scanner);
        Cedula cedula = Cedula.crearCedula(busca);
        boolean encontrado = false;
        for (Estudiante est : estudiantes) {
            if (est.cedula.equals(cedula) || est.nombre.equalsIgnoreCase(busca) || est.apellido.equalsIgnoreCase(busca)) {
                System.out.println(est.nombre + " " + est.apellido + " (" + est.id + ")");
                System.out.println("\tFecha de nacimiento: " + est.fechaNacimiento.get(Calendar.DAY_OF_MONTH) + "-" + (est.fechaNacimiento.get(Calendar.MONTH) + 1) + "-" + est.fechaNacimiento.get(Calendar.YEAR));
                System.out.println("\tEstado: " + est.estado);
                System.out.println("\tCarrera: " + est.carrera.getNombre() + " (" + est.carrera.getCodigo() + ")");
                System.out.println("\tCédula: " + est.cedula);
                System.out.println("\tEs extranjero: " + (est.esExtranjero ? "Si" : "No"));
                System.out.println();
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Estudiante no encontrado");
        }
        return true;
    }
}