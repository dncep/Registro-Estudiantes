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

        String codigo = Entrada.getString("Introduzca el código", scanner);

        for(Materia materia : registro.materias) {
            if (materia.codigo.equals(codigo)) {
                System.out.println(materia.codigo + " - " + materia.nombre);
                System.out.println("\tID: " + materia.id + " | Área: " + materia.area.getNombre());

                while(true) {
                    System.out.println("\nElige el numero del campo que quieres editar");
                    System.out.println("\n1. Código");
                    System.out.println("2. Nombre");
                    System.out.println("3. Área académica");
                    System.out.println("4. Salir");
                    int opcion = Entrada.getInt("\nIntroduzca el número de la opción", scanner, n -> n >= 1 && n <= 8);

                    switch(opcion) {
                        case 1:
                            materia.codigo = Entrada.getString("\nModificar el código: ", scanner);
                            System.out.println("\nModificado correctamente!");
                            break;
                        case 2:
                            materia.nombre = Entrada.getString("\nModificar el nombre: ", scanner);
                            System.out.println("\nModificado correctamente!");
                            break;
                        case 3:
                            materia.area = Entrada.getArea ("\nModificar el área académica: ", scanner);
                            System.out.println("\nModificado correctamente!");
                            break;
                        default:
                            System.out.println("\nNo ingresó una opción válida");
                        case 4:
                            return true;
                    }
                }
            }

        }  System.out.println("\nMateria no encontrada");
        return true;
    }
}