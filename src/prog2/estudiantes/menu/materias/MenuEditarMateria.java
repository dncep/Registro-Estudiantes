package prog2.estudiantes.menu.materias;

import prog2.estudiantes.data.Materia;
import prog2.estudiantes.data.Registro;
import prog2.estudiantes.menu.Entrada;
import prog2.estudiantes.menu.Menu;

import java.util.Scanner;

public class MenuEditarMateria implements Menu {
    @Override
    public String getNombre() {
        return "Editar Materia";
    }

    @Override
    public boolean seleccionar(Registro registro, Scanner scanner) {
        Materia mat = registro.getMateria("Introduzca el código de la materia", scanner);

        System.out.println(mat.codigo + " - " + mat.nombre);
        System.out.println("\tID: " + mat.id + " | Área: " + mat.area.getNombre());

        while(true) {
            System.out.println("\nElige el numero del campo que quieres editar\n");
            System.out.println("1. Código");
            System.out.println("2. Nombre");
            System.out.println("3. Área académica");
            System.out.println("4. Salir");
            int opcion = Entrada.getInt("\nIntroduzca el número de la opción", scanner, n -> n >= 1 && n <= 8);

            switch(opcion) {
                case 1:
                    mat.codigo = Entrada.getString("\nModificar el código", scanner);
                    System.out.println("\nModificado correctamente!");
                    break;
                case 2:
                    mat.nombre = Entrada.getString("\nModificar el nombre", scanner);
                    System.out.println("\nModificado correctamente!");
                    break;
                case 3:
                    mat.area = Entrada.getArea ("\nModificar el área académica", scanner);
                    System.out.println("\nModificado correctamente!");
                    break;
                default:
                    System.out.println("\nNo ingresó una opción válida");
                case 4:
                    return true;
            }
        }
    }
}