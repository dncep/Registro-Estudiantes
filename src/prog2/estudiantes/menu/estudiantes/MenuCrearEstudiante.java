package prog2.estudiantes.menu.estudiantes;

import prog2.estudiantes.data.Estudiante;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.Calendar;
import java.util.Scanner;

public class MenuCrearEstudiante implements Menu {
    @Override
    public String getNombre() {
        return "Crear estudiante";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        Estudiante estudiante = new Estudiante();
        int id = 0;
        Calendar calendar = Calendar.getInstance();
        id += calendar.get(Calendar.YEAR)%100;
        id *= 100;
        id += calendar.get(Calendar.MONTH)+1;
        id *= 100;
        id += calendar.get(Calendar.DAY_OF_MONTH);
        int sec = 1;
        for(Estudiante est : registro.estudiantes) {
            if(est.id/1000 == id) sec++;
        }
        id *= 1000;
        id += sec;
        estudiante.id = id;

        estudiante.nombre = Entrada.getString("Introduzca el nombre", scanner);
        estudiante.apellido = Entrada.getString("Introduzca el apellido", scanner);
        estudiante.fechaNacimiento = Entrada.getFecha("Introduzca la fecha de nacimiento", scanner);
        estudiante.estado = Entrada.getEstado("Introduzca el estado del estudiante", scanner);
        estudiante.carrera = Entrada.getCarrera("Introduzca la carrera del estudiante", scanner);
        estudiante.cedula = Entrada.getCedula("Introduzca la cédula del estudiante", scanner);
        estudiante.esExtranjero = Entrada.getBoolean("El estudiante es extranjero", scanner);
        registro.estudiantes.add(estudiante);
        System.out.println("Estudiante " + estudiante.nombre + " " + estudiante.apellido + " (" + estudiante.id + ") ha sido registrado correctamente");
        return true;
    }
}
