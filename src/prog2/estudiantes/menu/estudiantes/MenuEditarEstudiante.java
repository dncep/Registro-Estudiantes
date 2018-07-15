package prog2.estudiantes.menu.estudiantes;

import prog2.estudiantes.data.Estudiante;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.Calendar;
import java.util.Scanner;

public class MenuEditarEstudiante implements Menu {
    @Override
    public String getNombre() {
        return "Editar estudiante";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        Estudiante est = registro.getEstudiante("Introduzca el ID del estudiante", scanner);
        System.out.println("\t" + est.nombre + " " + est.apellido + " (" + est.id + ")");
        System.out.println("Fecha de nacimiento: " + est.fechaNacimiento.get(Calendar.DAY_OF_MONTH) + "-" + (est.fechaNacimiento.get(Calendar.MONTH)+1) + "-" + est.fechaNacimiento.get(Calendar.YEAR));
        System.out.println("Estado: " + est.estado);
        System.out.println("Carrera: " + est.carrera.getNombre() + " (" + est.carrera.getCodigo() + ")");
        System.out.println("Cedula: " + est.cedula);
        System.out.println("Es extranjero: " + (est.esExtranjero ? "Si" : "No"));
        System.out.println();
        while(true) {
            System.out.println("Elige el numero del campo que quieres editar");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Fecha de nacimiento");
            System.out.println("4. Estado");
            System.out.println("5. Carrera");
            System.out.println("6. Cedula");
            System.out.println("7. Es extranjero");
            System.out.println("8. Salir");
            int opcion = Entrada.getInt("Introduzca el número de la opción", scanner, n -> n >= 1 && n <= 8);

            switch(opcion) {
                case 1:
                    est.nombre = Entrada.getString("Introduzca el nombre", scanner);
                    break;
                case 2:
                    est.apellido = Entrada.getString("Introduzca el apellido", scanner);
                    break;
                case 3:
                    est.fechaNacimiento = Entrada.getFecha("Introduzca la fecha de nacimiento", scanner);
                    break;
                case 4:
                    est.estado = Entrada.getEstado("Introduzca el estado del estudiante", scanner);
                    break;
                case 5:
                    est.carrera = Entrada.getCarrera("Introduzca la carrera del estudiante", scanner);
                    break;
                case 6:
                    est.cedula = Entrada.getCedula("Introduzca la cédula del estudiante", scanner);
                    break;
                case 7:
                    est.esExtranjero = Entrada.getBoolean("El estudiante es extranjero", scanner);
                    break;
                default:
                    System.out.println("No ingresó una opción válida");
                case 8:
                    return true;
            }
        }
    }
}