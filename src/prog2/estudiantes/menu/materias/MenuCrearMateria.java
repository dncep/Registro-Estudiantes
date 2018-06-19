package prog2.estudiantes.menu.materias;

import prog2.estudiantes.data.Materia;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuCrearMateria implements Menu {
    @Override
    public String getNombre() {
        return "Crear Materia";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        Materia materia = new Materia();

        materia.id = registro.ID_MATERIA++;
        materia.codigo = Entrada.getString("Introduzca el código de la materia", scanner);
        materia.nombre = Entrada.getString("Introduzca el nombre de la materia", scanner);
        materia.area = Entrada.getArea("Introduzca el área académica de la materia", scanner);

        registro.materias.add(materia);

        System.out.println(materia.codigo + " - " + materia.nombre + " ha sido agregado correctamente");
        return true;
    }
}
