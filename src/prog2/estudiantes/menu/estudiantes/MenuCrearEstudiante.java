package prog2.estudiantes.menu.estudiantes;

import prog2.estudiantes.data.Estudiante;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuCrearEstudiante implements Menu {
    @Override
    public String getNombre() {
        return "Crear estudiante";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        ArrayList<Estudiante> estudiantes = registro.estudiantes;
        Estudiante estudiante = new Estudiante();
        estudiante.nombre = Entrada.getString("Introduzca el nombre", scanner);
        estudiante.apellido = Entrada.getString("Introduzca el apellido", scanner);
        estudiante.fechaNacimiento = Entrada.getFecha("Introduzca la fecha de nacimiento", scanner);
        estudiante.estado = Entrada.getEstado("Introduzca el estado del estudiante", scanner);
        estudiante.id = registro.ID_ESTUDIANTE++;//Entrada.getInt("Introduzca el ID o matrícula del estudiante", scanner, n -> n >= 0);
        estudiante.carrera = Entrada.getCarrera("Introduzca la carrera del estudiante", scanner);
        estudiante.cedula = Entrada.getCedula("Introduzca la cédula del estudiante", scanner);
        estudiante.esExtranjero = Entrada.getBoolean("El estudiante es extranjero", scanner);
        estudiantes.add(estudiante);
        System.out.println("Estudiante " + estudiante.nombre + " " + estudiante.apellido + " (" + estudiante.id + ") ha sido registrado correctamente");
        return true;
    }
}
